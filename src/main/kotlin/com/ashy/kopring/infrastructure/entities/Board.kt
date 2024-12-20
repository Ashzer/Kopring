package com.ashy.kopring.infrastructure.entities

import com.ashy.kopring.infrastructure.enums.BoardType

data class Board(val id: Board?, val title: String, val type: BoardType) {
    @JvmInline
    value class BoardId(val value: Int)
}
