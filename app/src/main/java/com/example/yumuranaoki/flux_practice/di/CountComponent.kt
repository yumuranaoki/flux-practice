package com.example.yumuranaoki.flux_practice.di

import com.example.yumuranaoki.flux_practice.flux.Dispatcher
import com.example.yumuranaoki.flux_practice.flux.action.CountAction
import com.example.yumuranaoki.flux_practice.flux.actionCreator.CountActionCreator
import com.example.yumuranaoki.flux_practice.flux.store.CountStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CountModule {
    @Singleton
    @Provides
    fun provideCountDispatcher() = Dispatcher<CountAction>()

    @Singleton
    @Provides
    fun provideCountStore(dispatcher: Dispatcher<CountAction>) = CountStore(dispatcher)

    @Provides
    fun provideCountActionCreator(dispatcher: Dispatcher<CountAction>) = CountActionCreator(dispatcher)
}