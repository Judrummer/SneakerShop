package com.agoda.sneakershop.screen.sneaker.list.interactor

import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListItemViewModel
import io.reactivex.Observable

interface SneakerListInteractor {
    fun getSneakers(query: String): Observable<List<SneakerListItemViewModel>>
}
