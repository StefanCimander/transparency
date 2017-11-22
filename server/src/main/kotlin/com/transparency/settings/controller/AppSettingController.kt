package com.transparency.settings.controller

import com.transparency.settings.service.AppSettingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/api/app-settings")
class AppSettingController {

    @Autowired
    lateinit var appSettingService: AppSettingService

    @GetMapping()
    fun getAll() = appSettingService.findAll()
}
