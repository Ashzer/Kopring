package com.ashy.kopring.infrastructure.middlewares

import an.awesome.pipelinr.Command
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(1)
class CommandLogMiddleware : Command.Middleware {
    override fun <R : Any?, C : Command<R>?> invoke(command: C, next: Command.Middleware.Next<R>?) = next?.invoke()
}