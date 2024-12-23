package com.ashy.kopring.infrastructure.services

import com.ashy.kopring.infrastructure.model.EmailDetailDto

interface EmailService {
    suspend fun sendEmail(emailDetailDto: EmailDetailDto): Result<Unit>
}