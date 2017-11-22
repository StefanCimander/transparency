package com.transparency.settings.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "app_settings")
data class AppSetting(@Id val name: String, var setting: String)