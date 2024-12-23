package com.ashy.kopring.infrastructure.constants

enum class ErrorConst(val code: String, val message: String) {
    INVALID_DATA("E00000", "Invalid data"),

    MEMBER_NOT_FOUND("E10000", "Member not found"), MEMBER_FAILED_TO_DELETE(
        "E10010", "Failed to delete member"
    ),
    MEMBER_CANNOT_BE_DELETED("E10020", "Member cannot be deleted"),
}