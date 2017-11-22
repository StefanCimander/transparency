package com.transparency.packages.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class PackageNotFoundException(val id: Long): RuntimeException("The package with id $id could not be found")
