package com.transparency.logical.entitiy

import javax.persistence.*

@Entity
@Table(name = "function_signal_links")
data class FunctionSignalLink(@Id val id: Long, @Enumerated(EnumType.STRING) val direction: SignalDirection) {

    @ManyToOne()
    @JoinColumn(name = "function_id")
    lateinit var logicalFunction: LogicalFunction

    @ManyToOne()
    @JoinColumn(name = "signal_id")
    lateinit var logicalSignal: LogicalSignal
}