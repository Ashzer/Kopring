package com.ashy.kopring.infrastructure.extensions

import com.ashy.kopring.infrastructure.failures.DatabaseFailure
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.LongIdTable
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
                DatabaseFailure.ConstraintViolationFailure(cause)
            }

            is SQLSyntaxErrorException -> {
                exposedLogger.error("$context | SQL syntax error: $e")
                DatabaseFailure.SQLSyntaxFailure(cause)
            }

            else -> {
                exposedLogger.error("$context | Unknown SQL error: $e")
                DatabaseFailure.UnknownSQLFailure(e)
            }
        }
        Result.failure(failure)
    } catch (e: Exception) {
        exposedLogger.error("$context | Unexpected Error: $e")
        Result.failure(DatabaseFailure.UnknownFailure)
    }
}

fun <T : IntIdTable> T.existById(id: Int) = this.select(this.id).where { this@existById.id eq id }.limit(1).count() > 0
fun <T : LongIdTable> T.existById(id: Long) =
    this.select(this.id).where { this@existById.id eq id }.limit(1).count() > 0
