package com.transparency.dao

import com.transparency.entity.PackageEntity
import com.transparency.exception.PackageNotFoundException
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

    fun findAllWithFeatureDependencies(): List<PackageEntity> {
        val session = sessionFactory.openSession()
        val packages = session.createCriteria(PackageEntity::class.java).list() as List<PackageEntity>
        packages.forEach {
            Hibernate.initialize(it.features)
            it.features.forEach { feature -> Hibernate.initialize(feature.linkedFeatures)}
        }
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
}