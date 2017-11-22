package com.transparency.features.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class FeatureNotFoundException(val id: Long): RuntimeException("The feature with id $id could not be found")