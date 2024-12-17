package com.ashy.kopring

import com.ashy.kopring.infrastructure.entities.MemberEntity
import org.jetbrains.exposed.spring.autoconfigure.ExposedAutoConfiguration
import org.jetbrains.exposed.sql.Schema
import org.jetbrains.exposed.sql.SchemaUtils
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@SpringBootApplication
@ImportAutoConfiguration(ExposedAutoConfiguration::class)
class KopringApplication

fun main(args: Array<String>) {
    runApplication<KopringApplication>(*args)
}

@Component
@Transactional
class SchemaInitialize : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        SchemaUtils.drop(MemberEntity)
        SchemaUtils.create(MemberEntity)
    }
}