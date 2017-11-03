package com.agoda.sneakershop.screen.sneaker.list.di

import com.agoda.sneakershop.screen.sneaker.list.SneakerListContract
import com.agoda.sneakershop.screen.sneaker.list.adapter.SneakerListAdapter
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides

@Module
class SneakerListTestModule {

    @Provides
    fun provideSneakerListAdapter(): SneakerListAdapter = mock()

    @Provides
    fun provideSneakerListPresenter(): SneakerListContract.Presenter = mock()

}