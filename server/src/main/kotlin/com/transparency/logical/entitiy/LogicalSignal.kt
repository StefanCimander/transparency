package com.transparency.logical.entitiy

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "logical_signals")
data class LogicalSignal(@Id val id: Long, val name: String)