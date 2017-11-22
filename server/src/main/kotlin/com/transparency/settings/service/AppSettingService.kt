package com.transparency.settings.service

import com.transparency.settings.dao.AppSettingDAO
import com.transparency.settings.transport.AppSettingTO

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppSettingService {

    @Autowired
    lateinit var appSettingDAO: AppSettingDAO

    fun findAll(): List<AppSettingTO> {
        return appSettingDAO.findAll().map(::AppSettingTO)
    }

    fun updateWithNameToNewValue(name: String, newValue: String) {
        val appSetting = appSettingDAO.findAll().first { it.name == name }
        appSetting.setting = newValue
        appSettingDAO.update(appSetting)
    }
}