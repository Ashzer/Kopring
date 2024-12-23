package com.ashy.kopring.infrastructure.failures

import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.sql.SQLIntegrityConstraintViolationException
import java.sql.SQLSyntaxErrorException

sealed class DatabaseFailure : Exception() {
    data class ConstraintViolationFailure(
        override val cause: SQLIntegrityConstraintViolationException
    ) : DatabaseFailure()

    data class SQLSyntaxFailure(
        override val cause: SQLSyntaxErrorException
    ) : DatabaseFailure()

    data class UnknownSQLFailure(
        override val cause: ExposedSQLException
    ) : DatabaseFailure()

    data object DataNotFoundFailure : DatabaseFailure() {
        private fun readResolve(): Any = DataNotFoundFailure
    }

    data object UnknownFailure : DatabaseFailure() {
        private fun readResolve(): Any = UnknownFailure
    }
}