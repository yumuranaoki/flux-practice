package com.example.yumuranaoki.flux_practice.di

import com.example.yumuranaoki.flux_practice.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CountModule::class])
interface CountComponent {
    fun inject(activity: MainActivity)
}