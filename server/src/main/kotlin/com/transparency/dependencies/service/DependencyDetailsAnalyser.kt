package com.transparency.dependencies.service

import com.transparency.dependencies.transport.DependencyDetailsTO
import com.transparency.dependencies.transport.DependencySignalTO
import com.transparency.features.transport.FeatureTO
import org.springframework.stereotype.Service

@Service
class DependencyDetailsAnalyser {

    fun findDetailsWithSourceAndTargetFeature(sourceFeature: FeatureTO, targetFeature: FeatureTO): DependencyDetailsTO {
        val signals = ArrayList<DependencySignalTO>()
        communicatedSignalsBetweenFeatures(sourceFeature, targetFeature).forEach { signal ->
            sourceFeature.logicalFunctions.filter { it.receivesSignal(signal) }.forEach { receivingFunction ->
                targetFeature.logicalFunctions.filter { it.sendsSignal(signal) }.forEach { sendingFunction ->
                    signals.add(DependencySignalTO(receivingFunction.name, sendingFunction.name, signal.name))
                }
            }
        }
        return DependencyDetailsTO(sourceFeature.name, targetFeature.name, signals)
    }

    private fun communicatedSignalsBetweenFeatures(sourceFeature: FeatureTO, targetFeature: FeatureTO) =
            sourceFeature.externalReceivingSignals().filter(targetFeature::sendsSignal)
}