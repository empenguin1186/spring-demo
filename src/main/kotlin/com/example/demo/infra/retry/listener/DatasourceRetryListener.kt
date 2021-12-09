package com.example.demo.infra.retry.listener

import org.slf4j.LoggerFactory
import org.springframework.retry.RetryCallback
import org.springframework.retry.RetryContext
import org.springframework.retry.listener.RetryListenerSupport
import org.springframework.stereotype.Component

@Component
class DatasourceRetryListener : RetryListenerSupport() {

    companion object {
        private val logger = LoggerFactory.getLogger(DatasourceRetryListener::class.java)
    }

    override fun <T : Any?, E : Throwable?> onError(
        context: RetryContext?,
        callback: RetryCallback<T, E>?,
        throwable: Throwable?
    ) {
        logger.error("RetryableException thrown.")
        super.onError(context, callback, throwable)
    }
}