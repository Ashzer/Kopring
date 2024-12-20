package com.ashy.kopring.infrastructure.entities

import com.ashy.kopring.infrastructure.enums.LoginType

data class Token(
    val id: Token?,
    val accessToken: String,
    val refreshToken: String,
    val loginType: LoginType,
    val socialAccessToken: String?,
    val socialRefreshToken: String?,
    val createdAt: String,
    val expiredAt: String,
    val memberId: Member.MemberId
) {
    @JvmInline
    value class TokenId(val value: Int)
}
