package com.ashy.kopring.infrastructure.entities

import org.jetbrains.exposed.dao.id.CompositeIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object TermPolicyAgreementEntity : CompositeIdTable("term_policy_agreement") {
    val memberId = reference("member_id", MemberEntity.id)
    val termPolicyId = reference("term_policy_id", TermPolicyEntity.id)
    val agreed = bool("agreed")
    val agreedAt = datetime("agreed_at")
    override val primaryKey = PrimaryKey(memberId, termPolicyId)
}