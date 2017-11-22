package com.transparency.settings.transport

import com.transparency.settings.entity.AppSetting

data class AppSettingTO(val name: String, val value: String) {

    constructor(entity: AppSetting): this(entity.name, entity.setting)
}