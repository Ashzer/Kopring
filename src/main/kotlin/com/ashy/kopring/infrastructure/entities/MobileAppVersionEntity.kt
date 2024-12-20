package com.ashy.kopring.infrastructure.entities

import com.ashy.kopring.infrastructure.enums.OsType
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object MobileAppVersionEntity : IntIdTable("mobile_app_version") {
    val version = varchar("version", length = 10)
    val os = enumerationByName("os", 10, OsType::class)
    val mandatory = bool("mandatory")
    val available = bool("available")
    val description = text("description")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}