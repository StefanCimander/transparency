package com.transparency.model

data class DependencyDetails(val source: Feature, val target: Feature, val causes: List<LogicalDependency>)