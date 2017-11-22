package com.transparency.dependencies.transport

import com.transparency.logical.transport.LogicalFunction
import com.transparency.logical.transport.LogicalSignal

data class LogicalDependencyTO(val source: LogicalFunction, val target: LogicalFunction, val signal: LogicalSignal)