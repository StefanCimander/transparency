package com.transparency.config

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.core.io.ClassPathResource

@Configuration
@PropertySource("application.properties")
class DataConfig {

    @Autowired
    private lateinit var environment: Environment

    @Bean
    fun sessionFactory(): org.springframework.orm.hibernate5.LocalSessionFactoryBean {
        val config = ClassPathResource("hibernate.cfg.xml")
        val sessionFactory = org.springframework.orm.hibernate5.LocalSessionFactoryBean()
        sessionFactory.setConfigLocation(config)
        sessionFactory.setPackagesToScan(environment.getProperty("transparency.entity.package"))
        sessionFactory.setDataSource(dataSource())
        return sessionFactory
    }

    @Bean
    fun dataSource(): BasicDataSource {
        val dataSource = BasicDataSource()
        dataSource.driverClassName = environment.getProperty("transparency.db.driver")
        dataSource.url = environment.getProperty("transparency.db.url")
        dataSource.username = environment.getProperty("transparency.db.username")
        dataSource.password = environment.getProperty("transparency.db.password")
        return dataSource
    }
}