package com.muzz.common

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

class TickerImpl @Inject constructor() : Ticker {

    override fun currentTimestamp(): Long {
        return System.currentTimeMillis()
    }

    override fun currentDateTime(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }
}