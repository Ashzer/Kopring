package com.ashy.kopring.infrastructure.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "member")
class MemberEntity (
    @Column
    var nickName : String,


    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Int? = null
)