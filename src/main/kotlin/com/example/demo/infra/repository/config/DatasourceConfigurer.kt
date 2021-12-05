package com.example.demo.infra.repository.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DatasourceConfigurer {

    @Bean
    fun datasource(properties: DataSourceProperties): DataSource {
        val datasource: HikariDataSource = properties
            .initializeDataSourceBuilder()
            .type(HikariDataSource::class.java)
            .build()

        datasource.password = "hoge"

        return datasource
    }
}