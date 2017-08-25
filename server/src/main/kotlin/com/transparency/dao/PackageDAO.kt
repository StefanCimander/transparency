package com.transparency.dao

import com.transparency.entity.PackageEntity
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
        packages.forEach { Hibernate.initialize(it.features) }
        session.close()
        return packages
    }

    fun findById(id: Long): PackageEntity {
        val session = sessionFactory.openSession()
        val packageEntity = session.get(PackageEntity::class.java, id)
        Hibernate.initialize(packageEntity.features)
        session.close()
        return packageEntity
    }
}