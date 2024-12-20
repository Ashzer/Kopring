package com.ashy.kopring.infrastructure.entities

import org.jetbrains.exposed.dao.id.IntIdTable

object TermPolicyEntity : IntIdTable("term_policy") {
    val title = varchar("title", length = 50)
    val content = text("content")
    val mandatory = bool("mandatory")
    val available = bool("available")
    val version = integer("version")
}