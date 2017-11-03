package com.agoda.sneakershop.di

import com.agoda.sneakershop.screen.sneaker.detail.di.SneakerDetailTestModule
import com.agoda.sneakershop.screen.sneaker.list.di.SneakerListTestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        SneakerListTestModule::class,
        SneakerDetailTestModule::class
))
interface TestAppComponent : AppComponent