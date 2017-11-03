package com.agoda.sneakershop.screen.sneaker.detail.interactor

import com.agoda.sneakershop.common.scheduler.SchedulerProvider
import com.agoda.sneakershop.data.repository.SneakerRepository
import com.agoda.sneakershop.screen.sneaker.detail.mapper.SneakerDetailViewModelMapper
import com.agoda.sneakershop.screen.sneaker.detail.viewmodel.SneakerDetailContentViewModel
import io.reactivex.Observable

class SneakerDetailInteractorImpl(private val sneakerRepository: SneakerRepository,
                                  private val schedulerProvider: SchedulerProvider,
                                  private val viewModelMapper: SneakerDetailViewModelMapper) : SneakerDetailInteractor {

    override fun getSneakerDetail(id: Long): Observable<SneakerDetailContentViewModel> =
            sneakerRepository.getSneakerDetail(id).map(viewModelMapper::map).toObservable()
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.main())

}