package com.muzz.common

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

interface Ticker {

    fun currentDateTime(): LocalDateTime

    fun currentTimezone(): TimeZone
}