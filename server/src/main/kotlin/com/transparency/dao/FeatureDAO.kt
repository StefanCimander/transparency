package com.transparency.dao

import com.transparency.entity.FeatureEntity
import org.hibernate.Hibernate
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class FeatureDAO {

    @Autowired
    private lateinit var sessionFactory: SessionFactory

    fun findAll(): List<FeatureEntity> {
        val session = sessionFactory.openSession()
        val features = session.createCriteria(FeatureEntity::class.java).list() as List<FeatureEntity>
        features.forEach {
            Hibernate.initialize(it.parentPackage)
            Hibernate.initialize(it.linkedFeatures)
        }
        session.close()
        return features
    }
}
