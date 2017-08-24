package model

class LogicalFunction(val id: Long, val name: String) {
    private var receivingSignals: MutableSet<LogicalSignal> = HashSet()
    private var sendingSignals: MutableSet<LogicalSignal> = HashSet()

    fun getReceivingSignals(): Set<LogicalSignal> {
        return HashSet(receivingSignals)
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
