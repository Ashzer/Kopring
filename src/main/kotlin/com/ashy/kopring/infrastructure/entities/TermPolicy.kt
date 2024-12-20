package com.ashy.kopring.infrastructure.entities

data class TermPolicy(
    val id: TermPolicyId?,
    val title: String,
    val content: String,
    val mandatory: Boolean,
    val available: Boolean,
) {
    @JvmInline
    value class TermPolicyId(val value: Int)
}
