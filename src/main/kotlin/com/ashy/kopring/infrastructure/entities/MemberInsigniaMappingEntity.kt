package com.ashy.kopring.infrastructure.entities

import org.jetbrains.exposed.dao.id.CompositeIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object MemberInsigniaMappingEntity : CompositeIdTable("member_insignia_mapping") {
    val memberId = reference("member_id", MemberEntity.id)
    val insigniaId = reference("insignia_id", InsigniaEntity.id)
    val acquiredAt = datetime("acquired_at")

    override val primaryKey = PrimaryKey(memberId, insigniaId)
}