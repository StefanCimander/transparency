package com.transparency.entity

import javax.persistence.*

@Entity
@Table(name = "function_signal_links")
data class FunctionSignalLinkEntity(@Id val id: Long, @Enumerated(EnumType.STRING) val direction: SignalDirection) {

    @ManyToOne()
    @JoinColumn(name = "function_id")
    lateinit var logicalFunction: LogicalFunctionEntity

    @ManyToOne()
    @JoinColumn(name = "signal_id")
    lateinit var logicalSignal: LogicalSignalEntity
}