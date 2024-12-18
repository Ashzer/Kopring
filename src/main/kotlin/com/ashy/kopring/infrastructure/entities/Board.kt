package com.ashy.kopring.infrastructure.entities

data class Board(val id: Board?, val title: String) {
    @JvmInline
    value class BoardId(val value: Int)
}
