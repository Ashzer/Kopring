package com.ashy.kopring.infrastructure.model

data class EmailDetailDto(
    val senderName : String?,
    val recipients : List<String>,
    val cc : List<String>,
    val subject : String,
    val body : String
)
