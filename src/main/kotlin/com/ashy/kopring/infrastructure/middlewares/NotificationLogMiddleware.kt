package com.ashy.kopring.infrastructure.middlewares

import an.awesome.pipelinr.Notification
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(1)
class NotificationLogMiddleware : Notification.Middleware {
    override fun <N : Notification?> invoke(notification: N, next: Notification.Middleware.Next?) {
        next?.invoke()
    }
}