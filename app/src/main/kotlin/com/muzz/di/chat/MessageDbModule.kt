package com.muzz.di.chat

import android.content.Context
import androidx.room.Room
import com.muzz.data.chat.db.MessageDao
import com.muzz.data.chat.db.MessageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object MessageDbModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext appContext: Context): MessageDatabase {
        return Room.databaseBuilder(appContext, MessageDatabase::class.java, MessageDatabase.NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideMessageDao(database: MessageDatabase): MessageDao {
        return database.messageDao()
    }
}