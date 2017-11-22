package com.transparency.settings.dao

import com.transparency.settings.entity.AppSetting
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class AppSettingDAO {

    @Autowired
    private lateinit var sessionFactory: SessionFactory

    fun findAll(): List<AppSetting> {
        val session = sessionFactory.openSession()
        val appSettings = session.createCriteria(AppSetting::class.java).list() as List<AppSetting>
        session.close()
        return appSettings
    }

    fun update(appSetting: AppSetting) {
        val session = sessionFactory.openSession()
        val transaction = session.beginTransaction()
        session.update(appSetting)
        transaction.commit()
        session.close()
    }
}