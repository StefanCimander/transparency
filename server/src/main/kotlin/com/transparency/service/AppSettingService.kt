package com.transparency.service

import com.transparency.dao.AppSettingDAO
import com.transparency.model.AppSetting

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppSettingService {

    @Autowired
    lateinit var appSettingDAO: AppSettingDAO

    fun findAll(): List<AppSetting> {
        return appSettingDAO.findAll().map(::AppSetting)
    }

    fun updateWithNameToNewValue(name: String, newValue: String) {
        val appSetting = appSettingDAO.findAll().first { it.name == name }
        appSetting.setting = newValue
        appSettingDAO.update(appSetting)
    }
}