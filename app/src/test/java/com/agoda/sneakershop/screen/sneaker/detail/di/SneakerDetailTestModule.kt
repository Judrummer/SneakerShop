package com.agoda.sneakershop.screen.sneaker.detail.di

import com.agoda.sneakershop.screen.sneaker.detail.SneakerDetailContract
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides

@Module
class SneakerDetailTestModule {

    @Provides
    fun provideSneakerDetailPresenter(): SneakerDetailContract.Presenter = mock()

}

