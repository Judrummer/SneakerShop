package com.agoda.sneakershop.screen.sneaker.detail

import com.agoda.sneakershop.screen.base.BaseContract
import com.agoda.sneakershop.screen.sneaker.detail.viewmodel.SneakerDetailViewModel

interface SneakerDetailContract {

    interface View : BaseContract.View<SneakerDetailViewModel>

    interface Presenter : BaseContract.Presenter<SneakerDetailViewModel, View> {
        fun fetchSneakerDetail(id:Long)
    }
}