package com.transparency.model

import com.transparency.entity.FeatureEntity

data class Dependency(val id: Long, val name: String, val type: DependencyType) {

    constructor(entity: FeatureEntity, type: DependencyType): this(entity.id, entity.name, type)
}
