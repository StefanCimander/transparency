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
            it.linkedFeatures = ArrayList()
            it.logicallyDependentFeatures = ArrayList()
            it.logicalFunctions = ArrayList()
        }
        session.close()
        return features
    }

    fun findAllWithLogicalFunctions(): List<FeatureEntity> {
        val session = sessionFactory.openSession()
        val features = session.createCriteria(FeatureEntity::class.java).list() as List<FeatureEntity>
        features.forEach {
            Hibernate.initialize(it.parentPackage)
            it.linkedFeatures = ArrayList()
            it.logicallyDependentFeatures = ArrayList()
            Hibernate.initialize(it.logicalFunctions)
            it.logicalFunctions.forEach {
                Hibernate.initialize(it.signalLinks)
            }
        }
        session.close()
        return features
    }

    fun findAllCompletelyInitialized(): List<FeatureEntity> {
        val session = sessionFactory.openSession()
        val features = session.createCriteria(FeatureEntity::class.java).list() as List<FeatureEntity>
        features.forEach {
            Hibernate.initialize(it.parentPackage)
            Hibernate.initialize(it.linkedFeatures)
            Hibernate.initialize(it.logicallyDependentFeatures)
            Hibernate.initialize(it.logicalFunctions)
            it.logicalFunctions.forEach {
                Hibernate.initialize(it.signalLinks)
            }
        }
        session.close()
        return features
    }

    fun update(feature: FeatureEntity) {
        val session = sessionFactory.openSession()
        val transaction = session.beginTransaction()
        session.update(feature)
        transaction.commit()
        session.close()
    }
}
