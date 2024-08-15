package com.muzz.domain

import kotlinx.datetime.LocalDateTime

data class Message(
    val id: String,
    val authorId: String,
    val dateTime: LocalDateTime,
    val message: String
)