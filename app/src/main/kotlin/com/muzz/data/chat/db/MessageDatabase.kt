package com.muzz.data.chat.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MessageDto::class], version = 1)
internal abstract class MessageDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao

    internal companion object {
        internal const val NAME = "database-messages"
    }
}