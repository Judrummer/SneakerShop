package com.agoda.sneakershop.screen.sneaker.detail.di

import com.agoda.sneakershop.common.scheduler.SchedulerProvider
import com.agoda.sneakershop.data.repository.SneakerRepository
import com.agoda.sneakershop.screen.sneaker.detail.SneakerDetailContract
import com.agoda.sneakershop.screen.sneaker.detail.SneakerDetailPresenter
import com.agoda.sneakershop.screen.sneaker.detail.interactor.SneakerDetailInteractor
import com.agoda.sneakershop.screen.sneaker.detail.interactor.SneakerDetailInteractorImpl
import com.agoda.sneakershop.screen.sneaker.detail.mapper.SneakerDetailViewModelMapper
import com.agoda.sneakershop.screen.sneaker.detail.mapper.SneakerDetailViewModelMapperImpl
import dagger.Module
import dagger.Provides

@Module
class SneakerDetailModule {

    @Provides
    fun provideSneakerDetailViewModelMapper(): SneakerDetailViewModelMapper =
            SneakerDetailViewModelMapperImpl()

    @Provides
    fun provideSneakerDetailInteractor(repository: SneakerRepository,
                                       schedulerProvider: SchedulerProvider,
                                       viewModelMapper: SneakerDetailViewModelMapper): SneakerDetailInteractor =
            SneakerDetailInteractorImpl(repository, schedulerProvider, viewModelMapper)

    @Provides
    fun provideSneakerDetailPresenter(interactor: SneakerDetailInteractor): SneakerDetailContract.Presenter =
            SneakerDetailPresenter(interactor)
}