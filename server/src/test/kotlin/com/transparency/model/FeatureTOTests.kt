package com.transparency.model

import com.transparency.features.transport.FeatureTO
import com.transparency.logical.transport.LogicalFunctionTO
import com.transparency.logical.transport.LogicalSignalTO
import org.junit.BeforeClass
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FeatureTOTests {

    companion object {
        private val feature1 = FeatureTO("1", 1L, "FeatureTO 1")
        private val feature2 = FeatureTO("2", 1L, "FeatureTO 2")
        private val feature3 = FeatureTO("3", 1L, "FeatureTO 3")

        private val logicalFunction1 = LogicalFunctionTO(1L, "Logical Function 1")
        private val logicalFunction2 = LogicalFunctionTO(2L, "Logical Function 2")
        private val logicalFunction3 = LogicalFunctionTO(3L, "Logical Function 3")
        private val logicalFunction4 = LogicalFunctionTO(4L, "Logical Function 4")
        private val logicalFunction5 = LogicalFunctionTO(5L, "Logical Function 5")
        private val logicalFunction6 = LogicalFunctionTO(6L, "Logical Function 6")
        private val logicalFunction7 = LogicalFunctionTO(7L, "Logical Function 7")

        private val logicalSignal1 = LogicalSignalTO(1L, "Logical Signal 1")
        private val logicalSignal2 = LogicalSignalTO(2L, "Logical Signal 2")
        private val logicalSignal3 = LogicalSignalTO(3L, "Logical Signal 3")
        private val logicalSignal4 = LogicalSignalTO(4L, "Logical Signal 4")
        private val logicalSignal5 = LogicalSignalTO(5L, "Logical Signal 5")
        private val logicalSignal6 = LogicalSignalTO(6L, "Logical Signal 6")

        @BeforeClass
        @JvmStatic fun setupTestArchitecture() {
            feature1.addLogicalFunction(logicalFunction1, logicalFunction2)
            feature2.addLogicalFunction(logicalFunction3, logicalFunction4, logicalFunction5)
            feature3.addLogicalFunction(logicalFunction5, logicalFunction6, logicalFunction7)

            logicalFunction1.addSendingSignal(logicalSignal1, logicalSignal2)
            logicalFunction2.addReceivingSignal(logicalSignal1)
            logicalFunction3.addSendingSignal(logicalSignal3)
            logicalFunction4.addReceivingSignal(logicalSignal2, logicalSignal4)
            logicalFunction5.addReceivingSignal(logicalSignal3, logicalSignal5)
            logicalFunction5.addSendingSignal(logicalSignal4, logicalSignal6)
            logicalFunction6.addSendingSignal(logicalSignal5)
            logicalFunction7.addReceivingSignal(logicalSignal6)
        }
    }

    @Test
    fun recognizesDependentFeature() {
        assertTrue(feature2.logicallyDependsOn(feature1))
    }

    @Test
    fun recognizeIndependentFeatures() {
        assertFalse(feature1.logicallyDependsOn(feature2))
        assertFalse(feature1.logicallyDependsOn(feature3))
        assertFalse(feature2.logicallyDependsOn(feature3))
        assertFalse(feature3.logicallyDependsOn(feature1))
        assertFalse(feature3.logicallyDependsOn(feature2))
    }

    @Test
    fun findsCorrectExternalReceivingSignals() {
        val expectedSignals = Arrays.asList(logicalSignal2, logicalSignal5)
        val actualSignals = feature2.externalReceivingSignals()
        assertEquals(expectedSignals.size.toLong(), actualSignals.size.toLong())
        assertTrue(actualSignals.containsAll(expectedSignals))
    }
}