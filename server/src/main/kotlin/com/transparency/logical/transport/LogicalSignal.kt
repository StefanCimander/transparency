package com.transparency.logical.transport

import com.transparency.logical.entitiy.LogicalSignalEntity

data class LogicalSignal(val id: Long, val name: String) {

    constructor(entity: LogicalSignalEntity): this(entity.id, entity.name)
}
