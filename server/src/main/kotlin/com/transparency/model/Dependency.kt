package com.transparency.model

import com.transparency.entity.FeatureEntity

data class Dependency(val id: Long, val name: String) {

    constructor(entity: FeatureEntity): this(entity.id, entity.name)
}
