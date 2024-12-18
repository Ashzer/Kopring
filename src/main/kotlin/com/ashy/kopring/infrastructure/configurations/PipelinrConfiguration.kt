package com.ashy.kopring.infrastructure.configurations

import an.awesome.pipelinr.*
import an.awesome.pipelinr.Command.Middleware
import org.springframework.beans.factory.ObjectProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PipelinrConfiguration {
    @Bean
    fun pipeline(
        commandHandlers: ObjectProvider<Command.Handler<*, *>>,
        notificationHandlers: ObjectProvider<Notification.Handler<*>>,
        middlewares: ObjectProvider<Middleware>
    ): Pipeline {
        return Pipelinr().with(CommandHandlers { commandHandlers.stream() })
            .with(NotificationHandlers { notificationHandlers.stream() })
            .with(Command.Middlewares { middlewares.orderedStream() })
    }
}