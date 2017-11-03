package com.agoda.sneakershop.screen.sneaker.list.di

import com.agoda.sneakershop.common.scheduler.SchedulerProvider
import com.agoda.sneakershop.data.repository.SneakerRepository
import com.agoda.sneakershop.screen.sneaker.list.SneakerListContract
import com.agoda.sneakershop.screen.sneaker.list.SneakerListPresenter
import com.agoda.sneakershop.screen.sneaker.list.adapter.SneakerListAdapter
import com.agoda.sneakershop.screen.sneaker.list.interactor.SneakerListInteractor
import com.agoda.sneakershop.screen.sneaker.list.interactor.SneakerListInteractorImpl
import com.agoda.sneakershop.screen.sneaker.list.mapper.SneakerListItemViewModelMapper
import com.agoda.sneakershop.screen.sneaker.list.mapper.SneakerListItemViewModelMapperImpl
import dagger.Module
import dagger.Provides

@Module
class SneakerListModule {

    @Provides
    fun provideSneakerListInteractor(sneakerRepository: SneakerRepository,
                                     schedulerProvider: SchedulerProvider,
                                     viewModelMapper: SneakerListItemViewModelMapper): SneakerListInteractor =
            SneakerListInteractorImpl(sneakerRepository, schedulerProvider, viewModelMapper)

    @Provides
    fun provideSneakerListItemViewModelMapper(): SneakerListItemViewModelMapper = SneakerListItemViewModelMapperImpl()

    @Provides
    fun provideSneakerListAdapter(): SneakerListAdapter = SneakerListAdapter()

    @Provides
    fun provideSneakerListPresenter(sneakerListInteractor: SneakerListInteractor): SneakerListContract.Presenter = SneakerListPresenter(sneakerListInteractor)

}