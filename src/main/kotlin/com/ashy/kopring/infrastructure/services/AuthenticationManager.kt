package com.ashy.kopring.infrastructure.services

import org.springframework.security.core.Authentication

interface AuthenticationManager {
    fun getAuthentication() : Authentication
}