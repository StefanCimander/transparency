package com.transparency.logical.transport

import com.transparency.logical.entitiy.LogicalFunction
import com.transparency.logical.entitiy.SignalDirection

class LogicalFunctionTO(val id: Long, val name: String) {
    private var receivingSignals: MutableList<LogicalSignalTO> = ArrayList()
    private var sendingSignals: MutableList<LogicalSignalTO> = ArrayList()

    constructor(entity: LogicalFunction): this(entity.id, entity.name) {
        entity.signalLinks.filter { it.direction == SignalDirection.RECEIVER }
                .forEach { addReceivingSignal(LogicalSignalTO(it.logicalSignal)) }
        entity.signalLinks.filter { it.direction == SignalDirection.SENDER }
                .forEach { addSendingSignal(LogicalSignalTO(it.logicalSignal)) }
    }

    fun getReceivingSignals(): List<LogicalSignalTO> = ArrayList(receivingSignals)

    fun addReceivingSignal(vararg signals: LogicalSignalTO) {
        signals.forEach { receivingSignals.add(it) }
    }

    fun addSendingSignal(vararg signals: LogicalSignalTO) {
        signals.forEach { sendingSignals.add(it) }
    }

    fun receivesSignal(signal: LogicalSignalTO): Boolean = receivingSignals.contains(signal)

    fun sendsSignal(signal: LogicalSignalTO): Boolean = sendingSignals.contains(signal)
}
