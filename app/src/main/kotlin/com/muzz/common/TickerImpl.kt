package com.muzz.common

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

class TickerImpl @Inject constructor() : Ticker {

    override fun currentDateTime(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(currentTimezone())
    }

    override fun currentTimezone(): TimeZone {
        return TimeZone.currentSystemDefault()
    }
}