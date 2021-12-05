package com.example.demo.infra.retry

import com.example.demo.domain.exception.DataSourceRetryableException
import com.example.demo.infra.retry.config.DataSourceRetryConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.retry.support.RetryTemplate
import org.springframework.retry.support.RetryTemplateBuilder

@Configuration
class RetryConfigurer {

    @Bean
    fun dataSourceRetryConfigurer(dataSourceRetryConfiguration: DataSourceRetryConfiguration): RetryTemplate {
        return RetryTemplateBuilder()
            .maxAttempts(dataSourceRetryConfiguration.maxRetryAttempt)
            .fixedBackoff(dataSourceRetryConfiguration.fixedBackOffMs)
            .retryOn(DataSourceRetryableException::class.java)
            .build()
    }
}