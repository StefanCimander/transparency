package com.transparency.model

class LogicalFunction(val id: Long, val name: String) {
    private var receivingSignals: MutableList<LogicalSignal> = ArrayList()
    private var sendingSignals: MutableList<LogicalSignal> = ArrayList()

    fun getReceivingSignals(): List<LogicalSignal> {
        return ArrayList(receivingSignals)
    }

    fun addReceivingSignal(vararg signals: LogicalSignal) {
        signals.forEach { receivingSignals.add(it) }
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
