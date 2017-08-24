package model;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FeatureTest {

    private static Feature feature1 = new Feature(1L, "Feature 1");
    private static Feature feature2 = new Feature(2L, "Feature 2");
    private static Feature feature3 = new Feature(3L, "Feature 3");

    private static LogicalFunction logicalFunction1 = new LogicalFunction(1L, "Logical Function 1");
    private static LogicalFunction logicalFunction2 = new LogicalFunction(2L, "Logical Function 2");
    private static LogicalFunction logicalFunction3 = new LogicalFunction(3L, "Logical Function 3");
    private static LogicalFunction logicalFunction4 = new LogicalFunction(4L, "Logical Function 4");
    private static LogicalFunction logicalFunction5 = new LogicalFunction(5L, "Logical Function 5");
    private static LogicalFunction logicalFunction6 = new LogicalFunction(6L, "Logical Function 6");
    private static LogicalFunction logicalFunction7 = new LogicalFunction(7L, "Logical Function 7");

    private static LogicalSignal logicalSignal1 = new LogicalSignal(1L, "Logical Signal 1");
    private static LogicalSignal logicalSignal2 = new LogicalSignal(2L, "Logical Signal 2");
    private static LogicalSignal logicalSignal3 = new LogicalSignal(3L, "Logical Signal 3");
    private static LogicalSignal logicalSignal4 = new LogicalSignal(4L, "Logical Signal 4");
    private static LogicalSignal logicalSignal5 = new LogicalSignal(5L, "Logical Signal 5");
    private static LogicalSignal logicalSignal6 = new LogicalSignal(6L, "Logical Signal 6");

    @BeforeClass
    public static void setupTestArchitecture() {
        setupFeatures();
        setupLogicalFunctions();
    }

    private static void setupFeatures() {
        feature1.addLogicalFunction(logicalFunction1, logicalFunction2);
        feature2.addLogicalFunction(logicalFunction3, logicalFunction4, logicalFunction5);
        feature3.addLogicalFunction(logicalFunction5, logicalFunction6, logicalFunction7);
    }

    private static void setupLogicalFunctions() {
        logicalFunction1.addSendingSignal(logicalSignal1, logicalSignal2);
        logicalFunction2.addReceivingSignal(logicalSignal1);
        logicalFunction3.addSendingSignal(logicalSignal3);
        logicalFunction4.addReceivingSignal(logicalSignal2, logicalSignal4);
        logicalFunction5.addReceivingSignal(logicalSignal3, logicalSignal5);
        logicalFunction5.addSendingSignal(logicalSignal4, logicalSignal6);
        logicalFunction6.addSendingSignal(logicalSignal5);
        logicalFunction7.addReceivingSignal(logicalSignal6);
    }

    @Test
    public void recognizesDependentFeature() {
        assertTrue(feature2.logicallyDependsOn(feature1));
    }

    @Test
    public void recognizeIndependentFeatures() {
        assertFalse(feature1.logicallyDependsOn(feature2));
        assertFalse(feature1.logicallyDependsOn(feature3));
        assertFalse(feature2.logicallyDependsOn(feature3));
        assertFalse(feature3.logicallyDependsOn(feature1));
        assertFalse(feature3.logicallyDependsOn(feature2));
    }

    @Test
    public void findsCorrectExternalReceivingSignals() {
        Collection<LogicalSignal> expectedSignals = Arrays.asList(logicalSignal2, logicalSignal5);
        Collection<LogicalSignal> actualSignals = feature2.externalReceivingSignals();
        assertEquals(expectedSignals.size(), actualSignals.size());
        assertTrue(actualSignals.containsAll(expectedSignals));
    }
}
