package com.example.demo.infra.retry

import com.example.demo.domain.exception.DataSourceRetryableException
import com.example.demo.infra.retry.config.DataSourceRetryConfiguration
import com.example.demo.infra.retry.listener.DatasourceRetryListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.retry.interceptor.RetryOperationsInterceptor
import org.springframework.retry.support.RetryTemplate
import org.springframework.retry.support.RetryTemplateBuilder

@Configuration
class RetryConfigurer {

    @Bean
    fun dataSourceRetryConfigurer(
        dataSourceRetryConfiguration: DataSourceRetryConfiguration,
        dataSourceRetryListener: DatasourceRetryListener
    ): RetryTemplate {
        return RetryTemplateBuilder()
            .maxAttempts(dataSourceRetryConfiguration.maxRetryAttempt)
            .fixedBackoff(dataSourceRetryConfiguration.fixedBackOffMs)
            .withListener(dataSourceRetryListener)
            .retryOn(DataSourceRetryableException::class.java)
            .build()
    }

    @Bean
    fun dataSourceRetryInterceptor(retryTemplate: RetryTemplate): RetryOperationsInterceptor {
        return RetryOperationsInterceptor().also {
            it.setRetryOperations(retryTemplate)
        }
    }
}