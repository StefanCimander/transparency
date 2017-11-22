package com.transparency.features

import com.transparency.features.exception.FeatureNotFoundException
import com.transparency.features.transport.FeatureTO

interface FeatureComponent {

    /**
     * Finds all features.
     *
     * @return a list of all features.
     */
    fun findAll(): List<FeatureTO>

    /**
     * Finds all features that have at least one logical dependency.
     *
     * @return a list of logically depending features.
     */
    fun findAllLogicallyDepending(): List<FeatureTO>

    /**
     * Finds the feature with a given id.
     *
     * @param id the feature identifier.
     * @return the feature with the according id.
     * @throws exception if there is no feature with such id.
     */
    @Throws(FeatureNotFoundException::class)
    fun findById(id: Long): FeatureTO
}