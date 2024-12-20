package com.ashy.kopring.infrastructure.extensions

import com.ashy.kopring.infrastructure.failures.DatabaseFailure
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.exposedLogger
import java.sql.SQLIntegrityConstraintViolationException
import java.sql.SQLSyntaxErrorException

fun <T> handleDatabaseOperation(operation: () -> T): Result<T> {
    val context = Thread.currentThread().stackTrace[2].let {
        "${it.className} in method name: ${it.methodName}"
    }
    exposedLogger.info("$context | Start")
    return try {
        Result.success(operation()).also {
            exposedLogger.info("$context | Completed Successfully")
        }
    } catch (e: ExposedSQLException) {
        val failure = when (val cause = e.cause) {
            is SQLIntegrityConstraintViolationException -> {
                exposedLogger.error("$context | SQL integrity constraint violation: $e")
                DatabaseFailure.ConstraintViolationFailure(
                    e.message ?: "SQL integrity constraint violation", cause
                )
            }

            is SQLSyntaxErrorException -> {
                exposedLogger.error("$context | SQL syntax error: $e")
                DatabaseFailure.SQLSyntaxFailure(
                    e.message ?: "SQL syntax error", cause
                )
            }

            else -> {
                exposedLogger.error("$context | Unknown SQL error: $e")
                DatabaseFailure.UnknownSQLFailure(
                    e.message ?: "Unknown SQL error", e
                )
            }
        }
        Result.failure(failure)
    } catch (e: Exception) {
        exposedLogger.error("$context | Unexpected Error: $e")
        Result.failure(DatabaseFailure.UnknownFailure(e.message ?: "Unexpected error", e))
    }
}
