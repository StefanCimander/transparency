package com.transparency.model

import com.transparency.entity.FeatureEntity

class Feature(val id: Long, val name: String) {
    var parentPackage: Package? = null
    private var logicalFunctions: MutableCollection<LogicalFunction> = HashSet()
    private var dependencies: MutableCollection<Dependency> = HashSet()

    constructor(entity: FeatureEntity): this(entity.id, entity.name) {
        parentPackage = Package(entity.parentPackage)
//        dependencies = unifiedDependenciesOf(entity)
    }
//
//    private fun unifiedDependenciesOf(entity: FeatureEntity): MutableCollection<Dependency> {
//        return HashSet(entity.linkedFeatures.map { Dependency(it.id, it.name, DependencyType.FEATURE_LINK) }
//                .union(entity.dependentFeatures.map { Dependency(it.id, it.name, DependencyType.LOGICAL_DEPENDENCY) }))
//    }

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
