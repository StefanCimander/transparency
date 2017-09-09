package com.transparency.dao

import com.transparency.entity.AppSettingEntity
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class AppSettingDAO {

    @Autowired
    private lateinit var sessionFactory: SessionFactory

    fun findAll(): List<AppSettingEntity> {
        val session = sessionFactory.openSession()
        val appSettings = session.createCriteria(AppSettingEntity::class.java).list() as List<AppSettingEntity>
        session.close()
        return appSettings
    }

    fun update(appSetting: AppSettingEntity) {
        val session = sessionFactory.openSession()
        val transaction = session.beginTransaction()
        session.update(appSetting)
        transaction.commit()
        session.close()
    }
}