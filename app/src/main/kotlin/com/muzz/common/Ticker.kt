package com.muzz.common

import kotlinx.datetime.LocalDateTime

interface Ticker {

    fun currentTimestamp(): Long

    fun currentDateTime(): LocalDateTime
}