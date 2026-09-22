package org.example.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = ["org.example.repository"])
class HibernateConfig {

    // ssh -p 2222 s466127@helios.cs.ifmo.ru -L 55080:localhost:55080 -L 55990:localhost:55990
    @Bean
    fun databaseProperties(
        @Value("\${database.url}") url: String,
        @Value("\${database.username}") username: String,
        @Value("\${database.password}") password: String,
        @Value("\${database.driver-class-name}") driverClassName: String,
    ): DatabaseProperties =
        DatabaseProperties(
            url = url,
            username = username,
            password = password,
            driverClassName = driverClassName,
        )

    @Bean
    fun dataSource(properties: DatabaseProperties): DataSource =
        DriverManagerDataSource().apply {
            setDriverClassName(properties.driverClassName)
            url = properties.url
            username = properties.username
            password = properties.password
        }

    @Bean
    fun entityManagerFactory(
        dataSource: DataSource,
    ): LocalContainerEntityManagerFactoryBean =
        LocalContainerEntityManagerFactoryBean().apply {
            setDataSource(dataSource)
            setPersistenceXmlLocation("classpath:META-INF/persistence.xml")
            persistenceUnitName = "moviesPersistenceUnit"
        }

    @Bean
    fun transactionManager(
        entityManagerFactory: EntityManagerFactory,
    ): PlatformTransactionManager =
        JpaTransactionManager(entityManagerFactory)

}