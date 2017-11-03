package com.agoda.sneakershop.common.di

import com.agoda.sneakershop.common.scheduler.SchedulerProvider
import com.agoda.sneakershop.common.scheduler.SchedulerProviderImpl
import dagger.Module
import dagger.Provides

@Module
class CommonModule {

    //Scheduler
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl()

}