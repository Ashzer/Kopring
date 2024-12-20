package com.ashy.kopring.infrastructure.entities

import java.time.OffsetDateTime

data class MemberInsigniaMapping(
    val memberId: Member.MemberId, val insigniaId: Insignia.InsigniaId, val acquiredAt: OffsetDateTime

)
