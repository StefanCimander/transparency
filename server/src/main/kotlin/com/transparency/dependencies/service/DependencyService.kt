package com.transparency.dependencies.service

import com.transparency.dependencies.DependencyComponent
import com.transparency.features.dao.FeatureDAO
import com.transparency.features.entity.Feature
import com.transparency.dependencies.transport.DependencyDetailsTO
import com.transparency.dependencies.transport.DependencyStatisticsTO
import com.transparency.features.FeatureComponent
import com.transparency.features.transport.FeatureTO
import com.transparency.settings.service.AppSettingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DependencyService: DependencyComponent {

    @Autowired
    lateinit var detailsAnalyser: DependencyDetailsAnalyser

    @Autowired
    lateinit var featureComponent: FeatureComponent

    @Autowired
    lateinit var featureDAO: FeatureDAO

    @Autowired
    lateinit var appSettingService: AppSettingService

    fun getDependencyStatistics(): DependencyStatisticsTO {
        val statistics = featureDAO.findAllIncludingDependencies()
                .map { Pair(it.linkedFeatures.size, it.logicallyDependentFeatures.size) }
                .reduce { acc, pair -> Pair(acc.first + pair.first, acc.second + pair.second) }
        return DependencyStatisticsTO(statistics.first, statistics.second)
    }

    fun analyseImplicitFeatureDependencies() {
        val allFeatures = featureDAO.findAllIncludingDependenciesAndLogicalFunctions()
        println("Analysing logical dependencies for ${allFeatures.size} features")
        allFeatures.forEach {
            val otherFeatures = ArrayList(allFeatures)
            otherFeatures.remove(it)
            updateLogicalDependenciesFor(it, otherFeatures)
        }
        appSettingService.updateWithNameToNewValue("status-implicit-dependencies", "analysed")
    }

    private fun updateLogicalDependenciesFor(feature: Feature, otherFeatures: List<Feature>) {
        otherFeatures.forEach {
            if (FeatureTO(feature).logicallyDependsOn(FeatureTO(it))) {
                println("Detected logical dependency: " + feature.name + " -> " + it.name)
                feature.logicallyDependentFeatures.add(it)
                featureDAO.update(feature)
            }
        }
    }

    fun deleteImplicitDependencies() {
        val allFeatures = featureDAO.findAllIncludingDependenciesAndLogicalFunctions()
        println("Deleting logical dependencies from " +
                "${allFeatures.filter { !it.logicallyDependentFeatures.isEmpty() }.size} features")
        allFeatures.forEach { removeLogicalDependenciesFor(it) }
        appSettingService.updateWithNameToNewValue("status-implicit-dependencies", "deleted")
    }

    private fun removeLogicalDependenciesFor(feature: Feature) {
        feature.logicallyDependentFeatures.clear()
        featureDAO.update(feature)
    }

    override fun findDetailsWithSourceAndTargetFeatureIds(sourceFeatureId: Long, targetFeatureId: Long): DependencyDetailsTO {
        val sourceFeature = featureComponent.findById(sourceFeatureId)
        val targetFeature = featureComponent.findById(targetFeatureId)
        return detailsAnalyser.findDetailsWithSourceAndTargetFeature(sourceFeature, targetFeature)
    }
}