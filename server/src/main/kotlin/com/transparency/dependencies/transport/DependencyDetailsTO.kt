package com.transparency.dependencies.transport

import com.transparency.features.transport.FeatureTO

data class DependencyDetailsTO(val source: FeatureTO, val target: FeatureTO, val causes: List<LogicalDependencyTO>)