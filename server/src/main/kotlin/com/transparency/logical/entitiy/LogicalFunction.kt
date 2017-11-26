package com.transparency.logical.entitiy

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "logical_functions")
data class LogicalFunction(@Id val id: Long, val name: String) {

    @OneToMany(mappedBy = "logicalFunction")
    lateinit var signalLinks: List<FunctionSignalLink>
}