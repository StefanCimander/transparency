package com.transparency.dependencies.transport

data class DependencyDetailsTO(
        val sourceFeature: String,
        val targetFeature: String,
        val signals: List<DependencySignalTO>
)