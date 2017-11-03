package com.agoda.sneakershop.screen.base

interface BaseContract {

    interface View<in VM> {

        fun onViewModelChanged(viewModel: VM)

    }

    interface Presenter<VM, in V : BaseContract.View<VM>> {

        val currentViewModel: VM

        fun attachView(view: V)

        fun detachView()

        fun restoreViewModel(viewModel: VM)

    }

}