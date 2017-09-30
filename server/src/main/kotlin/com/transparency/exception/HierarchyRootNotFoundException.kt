package com.transparency.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class HierarchyRootNotFoundException: RuntimeException("The root package of the hierarchy could not be found")
