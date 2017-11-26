package com.transparency.logical.transport

import com.transparency.logical.entitiy.LogicalSignal

data class LogicalSignalTO(val id: Long, val name: String) {

    constructor(entity: LogicalSignal): this(entity.id, entity.name)
}
