package com.ashy.kopring.infrastructure.services.impl

import com.ashy.kopring.infrastructure.services.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AuthenticationManagerImpl : AuthenticationManager {
    override fun getAuthentication(): Authentication = SecurityContextHolder.getContext().authentication
}