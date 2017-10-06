package com.transparency.service

import com.transparency.dao.FeatureDAO
import com.transparency.entity.FeatureEntity
import com.transparency.model.DependencyStatistics
import com.transparency.model.Feature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DependencyService {

    @Autowired
    lateinit var featureDAO: FeatureDAO

    @Autowired
    lateinit var appSettingService: AppSettingService

    fun getDependencyStatistics(): DependencyStatistics {
        val statistics = featureDAO.findAllWithLinkedAndLogicalDependentFeatures()
                .map { Pair(it.linkedFeatures.size, it.logicallyDependentFeatures.size) }
                .reduce { acc, pair -> Pair(acc.first + pair.first, acc.second + pair.second) }
        return DependencyStatistics(statistics.first, statistics.second)
    }

    fun analyseImplicitFeatureDependencies() {
        val allFeatures = featureDAO.findAllCompletelyInitialized()
        println("Analysing logical dependencies for ${allFeatures.size} features")
        allFeatures.forEach {
            val otherFeatures = ArrayList(allFeatures)
            otherFeatures.remove(it)
            updateLogicalDependenciesFor(it, otherFeatures)
        }
        appSettingService.updateWithNameToNewValue("status-implicit-dependencies", "analysed")
    }

    private fun updateLogicalDependenciesFor(feature: FeatureEntity, otherFeatures: List<FeatureEntity>) {
        otherFeatures.forEach {
            if (Feature(feature).logicallyDependsOn(Feature(it))) {
                println("Detected logical dependency: " + feature.name + " -> " + it.name)
                feature.logicallyDependentFeatures.add(it)
                featureDAO.update(feature)
            }
        }
    }

    fun deleteImplicitDependencies() {
        val allFeatures = featureDAO.findAllCompletelyInitialized()
        println("Deleting logical dependencies from " +
                "${allFeatures.filter { !it.logicallyDependentFeatures.isEmpty() }.size} features")
        allFeatures.forEach { removeLogicalDependenciesFor(it) }
        appSettingService.updateWithNameToNewValue("status-implicit-dependencies", "deleted")
    }

    private fun removeLogicalDependenciesFor(feature: FeatureEntity) {
        feature.logicallyDependentFeatures.clear()
        featureDAO.update(feature)
    }
}