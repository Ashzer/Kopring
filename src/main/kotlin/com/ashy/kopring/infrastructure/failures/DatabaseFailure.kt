package com.ashy.kopring.infrastructure.failures

import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.sql.SQLIntegrityConstraintViolationException
import java.sql.SQLSyntaxErrorException

sealed class DatabaseFailure : Exception() {
    data class ConstraintViolationFailure(
        override val message: String, override val cause: SQLIntegrityConstraintViolationException
    ) : DatabaseFailure()

    data class SQLSyntaxFailure(
        override val message: String, override val cause: SQLSyntaxErrorException
    ) : DatabaseFailure()

    data class UnknownSQLFailure(
        override val message: String, override val cause: ExposedSQLException
    ) : DatabaseFailure()

    data class DataNotFoundFailure(
        override val message: String, override val cause: Throwable
    ) : DatabaseFailure()

    data class UnknownFailure(
        override val message: String, override val cause: Throwable
    ) : DatabaseFailure()
}