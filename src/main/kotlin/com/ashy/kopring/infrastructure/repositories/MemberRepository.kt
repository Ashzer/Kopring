package com.ashy.kopring.infrastructure.repositories

import com.ashy.kopring.infrastructure.entities.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<MemberEntity, Int> {
}