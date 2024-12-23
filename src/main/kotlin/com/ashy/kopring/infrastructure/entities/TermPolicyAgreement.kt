package com.ashy.kopring.infrastructure.entities

import java.time.OffsetDateTime

data class TermPolicyAgreement(
    val id: TermPolicyAgreementId?,
    val termPolicyId: TermPolicy.TermPolicyId,
    val memberId: Member.MemberId,
    val agreedAt: OffsetDateTime
) {
    @JvmInline
    value class TermPolicyAgreementId(val value: Int)
}