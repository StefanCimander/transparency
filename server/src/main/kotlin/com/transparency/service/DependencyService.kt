package com.transparency.service

import com.transparency.dao.FeatureDAO
import com.transparency.entity.FeatureEntity
import com.transparency.model.Feature

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DependencyService {

    @Autowired
    lateinit var featureDAO: FeatureDAO

    fun analyseImplicitFeatureDependencies() {
        val allFeatures = featureDAO.findAllCompletelyInitialized()
        allFeatures.forEach {
            val otherFeatures = ArrayList(allFeatures)
            otherFeatures.remove(it)
            updateLogicalDependenciesFor(it, otherFeatures)
        }
    }

    private fun updateLogicalDependenciesFor(feature: FeatureEntity, otherFeatures: List<FeatureEntity>) {
        otherFeatures.forEach {
            if (Feature(feature).logicallyDependsOn(Feature(it))) {
                println("Detected logical dependency: " + feature.name + " -> " + it.name)
                it.logicallyDependentFeatures.add(it)
                featureDAO.update(it)
            }
        }
    }
}