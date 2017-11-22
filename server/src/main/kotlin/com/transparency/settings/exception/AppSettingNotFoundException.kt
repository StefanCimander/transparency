package com.transparency.settings.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class AppSettingNotFoundException: RuntimeException("The app setting could not be found")
