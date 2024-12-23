package com.ashy.kopring.infrastructure.extensions

import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneId

const val ZONE_ID = "+09:00"
fun now(): OffsetDateTime = OffsetDateTime.now(ZoneId.of(ZONE_ID))
fun startOfDay(): OffsetDateTime = OffsetDateTime.now(ZoneId.of(ZONE_ID)).withHour(0).withMinute(0).withSecond(0)
fun endOfDay(): OffsetDateTime = OffsetDateTime.now(ZoneId.of(ZONE_ID)).withHour(23).withMinute(59).withSecond(59)

fun LocalDate.toStartOfDay(): OffsetDateTime =
    this.atStartOfDay(ZoneId.of(ZONE_ID)).withHour(0).withMinute(0).withSecond(0).toOffsetDateTime()

fun LocalDate.toEndOfDay(): OffsetDateTime =
    this.atStartOfDay(ZoneId.of(ZONE_ID)).withHour(23).withMinute(59).withSecond(59).toOffsetDateTime()

fun OffsetDateTime.toStartOfDay(): OffsetDateTime = this.withHour(0).withMinute(0).withSecond(0)
fun OffsetDateTime.toEndOfDay(): OffsetDateTime = this.withHour(23).withMinute(59).withSecond(59)