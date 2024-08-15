package com.muzz.data.chat.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message")
internal class MessageDto(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    val author: String,
    val text: String,
    val createdAt: Long
)