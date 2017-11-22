package com.transparency.dependencies.transport

import com.transparency.features.entity.Feature

data class DependencyTO(val id: Long, val name: String, val type: DependencyType) {

    constructor(entity: Feature, type: DependencyType): this(entity.id, entity.name, type)
}
