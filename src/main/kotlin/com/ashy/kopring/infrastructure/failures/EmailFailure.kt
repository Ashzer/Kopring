package com.ashy.kopring.infrastructure.failures

sealed class EmailFailure : Exception() {
    data class EmailSendFailure(
        override val message: String, override val cause: Exception
    ) : EmailFailure()
}