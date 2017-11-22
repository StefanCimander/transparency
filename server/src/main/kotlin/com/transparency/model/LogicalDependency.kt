package com.transparency.model

data class LogicalDependency(val source: LogicalFunction, val target: LogicalFunction, val signal: LogicalSignal)