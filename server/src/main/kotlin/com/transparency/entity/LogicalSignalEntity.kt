package com.transparency.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "logical_signals")
data class LogicalSignalEntity(@Id val id: Long, val name: String)