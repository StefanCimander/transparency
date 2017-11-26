package com.transparency.features.transport

import com.transparency.features.entity.Feature
import com.transparency.dependencies.transport.DependencyTO
import com.transparency.dependencies.transport.DependencyType
import com.transparency.logical.transport.LogicalFunctionTO
import com.transparency.logical.transport.LogicalSignalTO

class FeatureTO(val id: Long, val parentPackageId: Long, val name: String) {
    var dependencies: MutableList<DependencyTO> = ArrayList()
    var logicalFunctions: MutableList<LogicalFunctionTO> = ArrayList()

    constructor(entity: Feature): this(entity.id, entity.parentPackage.id, entity.name) {
        entity.linkedFeatures.forEach { dependencies.add(DependencyTO(it, DependencyType.FEATURE_LINK)) }
        entity.logicallyDependentFeatures.forEach { dependencies.add(DependencyTO(it, DependencyType.LOGICAL_DEPENDENCY)) }
        entity.logicalFunctions.forEach { logicalFunctions.add(LogicalFunctionTO(it)) }
    }

    fun addLogicalFunction(vararg functions: LogicalFunctionTO) {
        functions.forEach { logicalFunctions.add(it) }
    }

    fun logicallyDependsOn(otherFeature: FeatureTO): Boolean {
        return externalReceivingSignals()
                .any { otherFeature.sendsSignal(it) && !otherFeature.receivesSignal(it) }
    }

    fun externalReceivingSignals(): Collection<LogicalSignalTO> {
        return getReceivingSignals().filter { !sendsSignal(it) }
    }

    fun sendsSignal(signal: LogicalSignalTO): Boolean {
        return logicalFunctions.any { it.sendsSignal(signal) }
    }

    private fun getReceivingSignals(): Collection<LogicalSignalTO> {
        return logicalFunctions.flatMap { it.getReceivingSignals() }
    }

    private fun receivesSignal(signal: LogicalSignalTO): Boolean {
        return logicalFunctions.any { it.receivesSignal(signal) }
    }
}
