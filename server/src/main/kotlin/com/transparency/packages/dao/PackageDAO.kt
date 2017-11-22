package com.transparency.packages.dao

import com.transparency.features.entity.Feature
import com.transparency.packages.entity.Package
import com.transparency.packages.exception.PackageNotFoundException
import com.transparency.dependencies.transport.DependenciesRequest
import org.hibernate.Hibernate
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class PackageDAO {

    @Autowired
    private lateinit var sessionFactory: SessionFactory

    fun findAll(): List<Package> {
        val session = sessionFactory.openSession()
        val packages = session.createCriteria(Package::class.java).list() as List<Package>
        packages.forEach { it.features = ArrayList() }
        session.close()
        return packages
    }

    @Throws(PackageNotFoundException::class)
    fun findById(id: Long): Package {
        val session = sessionFactory.openSession()
        val packageEntity = session.get(Package::class.java, id) ?: throw PackageNotFoundException(id)
        packageEntity.features = ArrayList()
        session.close()
        return packageEntity
    }

    fun findAllWithFeatureDependencies(dependenciesRequest: DependenciesRequest): List<Package> {
        val session = sessionFactory.openSession()
        val packages = session.createCriteria(Package::class.java).list() as List<Package>
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

    private fun initializeDependencies(feature: Feature, dependenciesRequest: DependenciesRequest) {
        when (dependenciesRequest) {
            DependenciesRequest.ONLY_EXPLICIT -> initializeOnlyLinkedFeatures(feature)
            DependenciesRequest.ONLY_IMPLICIT -> initializeOnlyLogicallyDependentFeatures(feature)
            else -> initializeAllDependencies(feature)
        }
    }

    private fun initializeAllDependencies(feature: Feature) {
        Hibernate.initialize(feature.linkedFeatures)
        Hibernate.initialize(feature.logicallyDependentFeatures)
    }

    private fun initializeOnlyLinkedFeatures(feature: Feature) {
        Hibernate.initialize(feature.linkedFeatures)
        feature.logicallyDependentFeatures = ArrayList()
    }

    private fun initializeOnlyLogicallyDependentFeatures(feature: Feature) {
        Hibernate.initialize(feature.logicallyDependentFeatures)
        feature.linkedFeatures = ArrayList()
    }

    private fun filterForNonConformalDependencies(packages: List<Package>) {
        packages.flatMap{ it.features }.forEach { feature ->
            val intersect = feature.linkedFeatures.intersect(feature.logicallyDependentFeatures)
            feature.linkedFeatures = feature.linkedFeatures.filter { !intersect.contains(it) }
            feature.logicallyDependentFeatures.removeIf { intersect.contains(it) }
        }
    }

    private fun filterForConformalDependencies(packages: List<Package>) {
        packages.flatMap { it.features }.forEach { feature ->
            val intersect = feature.linkedFeatures.intersect(feature.logicallyDependentFeatures)
            feature.linkedFeatures = feature.linkedFeatures.filter { intersect.contains(it) }
            feature.logicallyDependentFeatures.removeIf { !intersect.contains(it) }
        }
    }
}