package com.agoda.sneakershop.screen.sneaker.list

import com.agoda.sneakershop.screen.base.BaseContract
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListItemViewModel
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListViewModel

interface SneakerListContract {

    interface View : BaseContract.View<SneakerListViewModel> {
        fun openSneakerDetail(id: Long)
    }

    interface Presenter : BaseContract.Presenter<SneakerListViewModel, SneakerListContract.View> {
        fun onSneakerClick(position:Int)
        fun fetchSneakers(query: String)
    }

}