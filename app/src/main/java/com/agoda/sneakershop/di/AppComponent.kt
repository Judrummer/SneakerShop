package com.agoda.sneakershop.di

import com.agoda.sneakershop.common.di.CommonModule
import com.agoda.sneakershop.data.di.DataModule
import com.agoda.sneakershop.screen.sneaker.detail.SneakerDetailFragment
import com.agoda.sneakershop.screen.sneaker.detail.di.SneakerDetailModule
import com.agoda.sneakershop.screen.sneaker.list.SneakerListFragment
import com.agoda.sneakershop.screen.sneaker.list.di.SneakerListModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        CommonModule::class,
        DataModule::class,
        SneakerListModule::class,
        SneakerDetailModule::class
))
interface AppComponent {
    fun inject(sneakerListFragment: SneakerListFragment)
    fun inject(sneakerDetailFragment: SneakerDetailFragment)
}