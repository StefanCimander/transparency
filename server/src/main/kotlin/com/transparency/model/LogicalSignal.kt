package com.transparency.model

import com.transparency.entity.LogicalSignalEntity

data class LogicalSignal(val id: Long, val name: String) {

    constructor(entity: LogicalSignalEntity): this(entity.id, entity.name)
}
