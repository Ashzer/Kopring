package com.ashy.kopring.infrastructure.entities

import com.ashy.kopring.infrastructure.enums.InsigniaType

data class Insignia(
    val id: InsigniaId?, val name: String, val description: String, val imageURL: String, val type: InsigniaType
) {
    @JvmInline
    value class InsigniaId(val value: Int)
}
