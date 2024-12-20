package com.ashy.kopring.infrastructure.entities

import com.ashy.kopring.infrastructure.enums.BoardType
import org.jetbrains.exposed.dao.id.IntIdTable

object BoardEntity : IntIdTable("board") {
    val title = varchar("title", length = 50)
    val type = enumerationByName("type", 10, BoardType::class)
}