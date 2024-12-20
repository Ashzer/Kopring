package com.ashy.kopring.infrastructure.entities

import com.ashy.kopring.infrastructure.enums.OsType
import java.time.OffsetDateTime

data class MobileAppVersion(
    val id: MobileAppVersionId?,
    val version: String,
    val osType: OsType,
    val mandatory: Boolean,
    val available: Boolean,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
) {
    @JvmInline
    value class MobileAppVersionId(val value: Int)
}

