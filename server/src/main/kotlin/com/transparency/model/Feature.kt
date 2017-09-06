package com.transparency.model

import com.transparency.entity.FeatureEntity

class Feature(val id: Long, val parentPackageId: Long, val name: String) {
    var dependencies: MutableList<Dependency> = ArrayList()
    var logicalFunctions: MutableList<LogicalFunction> = ArrayList()

    constructor(entity: FeatureEntity): this(entity.id, entity.parentPackage.id, entity.name) {
        entity.linkedFeatures.forEach { dependencies.add(Dependency(it, DependencyType.FEATURE_LINK)) }
        entity.logicallyDependentFeatures.forEach { dependencies.add(Dependency(it, DependencyType.LOGICAL_DEPENDENCY)) }
        entity.logicalFunctions.forEach { logicalFunctions.add(LogicalFunction(it)) }
    }

    fun addLogicalFunction(vararg functions: LogicalFunction) {
        functions.forEach { logicalFunctions.add(it) }
    }

    fun logicallyDependsOn(otherFeature: Feature): Boolean {
        return externalReceivingSignals()
                .any { otherFeature.sendsSignal(it) && !otherFeature.receivesSignal(it) }
    }

    fun externalReceivingSignals(): Collection<LogicalSignal> {
        return getReceivingSignals().filter { !sendsSignal(it) }
    }

    private fun getReceivingSignals(): Collection<LogicalSignal> {
        return logicalFunctions.flatMap { it.getReceivingSignals() }
    }

    private fun sendsSignal(signal: LogicalSignal): Boolean {
        return logicalFunctions.any { it.sendsSignal(signal) }
    }

    private fun receivesSignal(signal: LogicalSignal): Boolean {
        return logicalFunctions.any { it.receivesSignal(signal) }
    }
}
