package com.transparency.dao

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister
import com.transparency.entity.FeatureEntity
import com.transparency.entity.PackageEntity
import com.transparency.exception.PackageNotFoundException
import com.transparency.model.Feature
import com.transparency.service.DependenciesRequest
import org.hibernate.Hibernate
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class PackageDAO {

    @Autowired
    private lateinit var sessionFactory: SessionFactory

    fun findAll(): List<PackageEntity> {
        val session = sessionFactory.openSession()
        val packages = session.createCriteria(PackageEntity::class.java).list() as List<PackageEntity>
        packages.forEach { it.features = ArrayList() }
        session.close()
        return packages
    }

    @Throws(PackageNotFoundException::class)
    fun findById(id: Long): PackageEntity {
        val session = sessionFactory.openSession()
        val packageEntity = session.get(PackageEntity::class.java, id) ?: throw PackageNotFoundException(id)
        packageEntity.features = ArrayList()
        session.close()
        return packageEntity
    }

    fun findAllWithFeatureDependencies(dependenciesRequest: DependenciesRequest): List<PackageEntity> {
        val session = sessionFactory.openSession()
        val packages = session.createCriteria(PackageEntity::class.java).list() as List<PackageEntity>
        packages.forEach {
            Hibernate.initialize(it.features)
            it.features.forEach {
                initializeDependencies(it, dependenciesRequest)
                it.logicalFunctions = ArrayList()
            }
        }
        session.close()

        if (dependenciesRequest == DependenciesRequest.NON_CONFORMAL) {
            filterForNonConformalDependencies(packages)
        } else if (dependenciesRequest == DependenciesRequest.CONFORMAL) {
            filterForConformalDependencies(packages)
        }
        return packages
    }

    private fun initializeDependencies(feature: FeatureEntity, dependenciesRequest: DependenciesRequest) {
        when (dependenciesRequest) {
            DependenciesRequest.ONLY_EXPLICIT -> initializeOnlyLinkedFeatures(feature)
            DependenciesRequest.ONLY_IMPLICIT -> initializeOnlyLogicallyDependentFeatures(feature)
            else -> initializeAllDependencies(feature)
        }
    }

    private fun initializeAllDependencies(feature: FeatureEntity) {
        Hibernate.initialize(feature.linkedFeatures)
        Hibernate.initialize(feature.logicallyDependentFeatures)
    }

    private fun initializeOnlyLinkedFeatures(feature: FeatureEntity) {
        Hibernate.initialize(feature.linkedFeatures)
        feature.logicallyDependentFeatures = ArrayList()
    }

    private fun initializeOnlyLogicallyDependentFeatures(feature: FeatureEntity) {
        Hibernate.initialize(feature.logicallyDependentFeatures)
        feature.linkedFeatures = ArrayList()
    }

    private fun filterForNonConformalDependencies(packages: List<PackageEntity>) {
        packages.flatMap{ it.features }.forEach { feature ->
            val intersect = feature.linkedFeatures.intersect(feature.logicallyDependentFeatures)
            feature.linkedFeatures = feature.linkedFeatures.filter { !intersect.contains(it) }
            feature.logicallyDependentFeatures.removeIf { intersect.contains(it) }
        }
    }

    private fun filterForConformalDependencies(packages: List<PackageEntity>) {
        packages.flatMap { it.features }.forEach { feature ->
            val intersect = feature.linkedFeatures.intersect(feature.logicallyDependentFeatures)
            feature.linkedFeatures = feature.linkedFeatures.filter { intersect.contains(it) }
            feature.logicallyDependentFeatures.removeIf { !intersect.contains(it) }
        }
    }
}