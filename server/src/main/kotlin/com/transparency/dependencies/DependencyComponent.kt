package com.transparency.dependencies

import com.transparency.dependencies.transport.DependencyDetailsTO

interface DependencyComponent {

    /**
     * Finds dependency details with the given source and target feature ids.
     *
     * @param sourceFeatureId the dependency's source feature identifier.
     * @param targetFeatureId the dependency's target feature identifier.
     * @return the details of the dependency between the two features.
     */
    fun findDetailsWithSourceAndTargetFeatureIds(sourceFeatureId: Long, targetFeatureId: Long): DependencyDetailsTO
}