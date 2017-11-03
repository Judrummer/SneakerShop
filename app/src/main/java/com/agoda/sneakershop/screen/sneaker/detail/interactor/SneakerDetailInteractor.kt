package com.agoda.sneakershop.screen.sneaker.detail.interactor

import com.agoda.sneakershop.screen.sneaker.detail.viewmodel.SneakerDetailContentViewModel
import io.reactivex.Observable

interface SneakerDetailInteractor {
    fun getSneakerDetail(id: Long): Observable<SneakerDetailContentViewModel>
}