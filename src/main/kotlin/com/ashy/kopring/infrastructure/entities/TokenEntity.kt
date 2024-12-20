package com.ashy.kopring.infrastructure.entities

import com.ashy.kopring.infrastructure.enums.LoginType
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object TokenEntity : LongIdTable("token") {
    val memberId = reference("member_id", MemberEntity.id)
    val accessToken = varchar("access_token", length = 100)
    val refreshToken = varchar("refresh_token", length = 100)
    val loginType = enumerationByName("login_type", 10, LoginType::class).default(LoginType.LOCAL)
    val socialAccessToken = varchar("social_access_token", length = 100).nullable()
    val socialRefreshToken = varchar("social_refresh_token", length = 100).nullable()
    val createdAt = datetime("created_at")
    val expiredAt = datetime("expired_at")
}