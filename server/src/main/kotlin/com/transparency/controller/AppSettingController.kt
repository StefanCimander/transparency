package com.transparency.controller

import com.transparency.model.AppSetting
import com.transparency.service.AppSettingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api/app-settings")
class AppSettingController {

    @Autowired
    lateinit var appSettingService: AppSettingService

    @GetMapping()
    fun getAll(): List<AppSetting> {
        return appSettingService.findAll()
    }
}