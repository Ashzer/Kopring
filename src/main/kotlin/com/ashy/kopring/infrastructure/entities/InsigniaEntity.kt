package com.ashy.kopring.infrastructure.entities

import com.ashy.kopring.infrastructure.entities.ArticleEntity.enumerationByName
import com.ashy.kopring.infrastructure.entities.ArticleEntity.varchar
import com.ashy.kopring.infrastructure.enums.InsigniaType
import org.jetbrains.exposed.dao.id.IntIdTable

object InsigniaEntity : IntIdTable("insignia") {
    val name = varchar("name", length = 50)
    val description = varchar("description", length = 100)
    val imageUrl = varchar("image_url", length = 100)
    val type = enumerationByName("type", 20, InsigniaType::class)
}