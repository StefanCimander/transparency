package com.transparency.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "app_settings")
data class AppSettingEntity(@Id val name: String, var setting: String)