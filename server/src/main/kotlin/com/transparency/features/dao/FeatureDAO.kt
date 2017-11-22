package com.transparency.features.dao

import com.transparency.features.entity.Feature
import com.transparency.features.exception.FeatureNotFoundException
import org.hibernate.Hibernate
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class FeatureDAO {

    @Autowired
    private lateinit var sessionFactory: SessionFactory

    fun findAll(): List<Feature> {
        val session = sessionFactory.openSession()
        val features = session.createCriteria(Feature::class.java).list() as List<Feature>
        features.forEach {
            Hibernate.initialize(it.parentPackage)
            it.linkedFeatures = emptyList()
            it.logicallyDependentFeatures = ArrayList()
            it.logicalFunctions = emptyList()
        }
        session.close()
        return features
    }

    fun findAllIncludingDependencies(): List<Feature> {
        val session = sessionFactory.openSession()
        val features = session.createCriteria(Feature::class.java).list() as List<Feature>
        features.forEach {
            Hibernate.initialize(it.linkedFeatures)
            Hibernate.initialize(it.logicallyDependentFeatures)
            it.logicalFunctions = emptyList()
        }
        session.close()
        return features
    }

    fun findAllIncludingDependenciesAndLogicalFunctions(): List<Feature> {
        val session = sessionFactory.openSession()
        val features = session.createCriteria(Feature::class.java).list() as List<Feature>
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

    /**
     * Finds the feature with a given id.
     *
     * @param id the feature identifier.
     * @return the feature with the according id.
     * @throws exception if there is no feature with such id.
     */
    @Throws(FeatureNotFoundException::class)
    fun findById(id: Long): Feature {
        val session = sessionFactory.openSession()
        val feature = session.get(Feature::class.java, id) ?: throw FeatureNotFoundException(id)
        feature.linkedFeatures = emptyList()
        feature.logicallyDependentFeatures = ArrayList()
        Hibernate.initialize(feature.logicalFunctions)
        feature.logicalFunctions.forEach {
            Hibernate.initialize(it.signalLinks)
        }
        session.close()
        return feature
    }

    /**
     * Updates a given feature.
     *
     * @param feature the feature to update.
     */
    fun update(feature: Feature) {
        val session = sessionFactory.openSession()
        val transaction = session.beginTransaction()
        session.update(feature)
        transaction.commit()
        session.close()
    }
}
