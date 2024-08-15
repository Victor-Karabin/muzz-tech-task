package com.muzz.di.utils

import com.muzz.common.Ticker
import com.muzz.common.TickerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface TickerModule {

    @Binds
    fun bindTicker(dateTime: TickerImpl): Ticker
}