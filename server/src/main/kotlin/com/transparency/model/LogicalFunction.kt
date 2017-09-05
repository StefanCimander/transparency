package com.transparency.model

import com.transparency.entity.LogicalFunctionEntity
import com.transparency.entity.SignalDirection

class LogicalFunction(val id: Long, val name: String) {
    private var receivingSignals: MutableList<LogicalSignal> = ArrayList()
    private var sendingSignals: MutableList<LogicalSignal> = ArrayList()

    constructor(entity: LogicalFunctionEntity): this(entity.id, entity.name) {
        entity.signalLinks.filter { it.direction == SignalDirection.RECEIVER }
                .forEach { addReceivingSignal(LogicalSignal(it.logicalSignal)) }
        entity.signalLinks.filter { it.direction == SignalDirection.SENDER }
                .forEach { addSendingSignal(LogicalSignal(it.logicalSignal)) }
    }

    fun getReceivingSignals(): List<LogicalSignal> {
        return ArrayList(receivingSignals)
    }

    fun addReceivingSignal(vararg signals: LogicalSignal) {
        signals.forEach { receivingSignals.add(it) }
    }

    fun getSendingSignals(): List<LogicalSignal> {
        return ArrayList(sendingSignals)
    }

    fun addSendingSignal(vararg signals: LogicalSignal) {
        signals.forEach { sendingSignals.add(it) }
    }

    fun receivesSignal(signal: LogicalSignal): Boolean {
        return receivingSignals.contains(signal)
    }

    fun sendsSignal(signal: LogicalSignal): Boolean {
        return sendingSignals.contains(signal)
    }
}
