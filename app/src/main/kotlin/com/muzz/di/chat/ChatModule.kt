package com.muzz.di.chat

import com.muzz.data.chat.MessageRepo
import com.muzz.data.chat.MessageRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface ChatModule {

    @Binds
    fun bindMessageRepo(repo: MessageRepoImpl): MessageRepo
}