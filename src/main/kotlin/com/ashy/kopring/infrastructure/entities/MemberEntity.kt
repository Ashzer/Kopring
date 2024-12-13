package com.ashy.kopring.infrastructure.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id

@Entity
class MemberEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Int? = null
}