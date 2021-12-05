package com.example.demo.infra.retry.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DatasourceRetryConfig {

    @Bean
    @ConfigurationProperties(prefix = "demo.datasource.retrypolicy")
    fun dataSourceConfiguration(): DataSourceRetryConfiguration {
        return DataSourceRetryConfiguration()
    }
}

class DataSourceRetryConfiguration {
    var maxRetryAttempt: Int = 0
    var fixedBackOffMs: Long = 0
}