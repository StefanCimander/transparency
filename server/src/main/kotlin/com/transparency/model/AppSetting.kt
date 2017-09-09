package com.transparency.model

import com.transparency.entity.AppSettingEntity

data class AppSetting(val name: String, val value: String) {

    constructor(entity: AppSettingEntity): this(entity.name, entity.setting)
}