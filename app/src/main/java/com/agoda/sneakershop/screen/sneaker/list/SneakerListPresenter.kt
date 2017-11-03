package com.agoda.sneakershop.screen.sneaker.list

import com.agoda.sneakershop.common.extension.plusAssign
import com.agoda.sneakershop.screen.base.BasePresenter
import com.agoda.sneakershop.screen.sneaker.list.interactor.SneakerListInteractor
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListViewModel

class SneakerListPresenter(private val sneakerListInteractor: SneakerListInteractor)
    : BasePresenter<SneakerListViewModel, SneakerListContract.View>(),
        SneakerListContract.Presenter {

    override fun defaultViewModel(): SneakerListViewModel = SneakerListViewModel(listOf(), false, null, "")

    override fun fetchSneakers(query: String) {
//        viewModel = viewModel.copy(loading = true, error = null, query = query)
//
//        disposables += sneakerListInteractor.getSneakers(query).subscribe({
//            viewModel = viewModel.copy(items = it, loading = false)
//        }, {
//            viewModel = viewModel.copy(error = it, loading = false)
//        })

        disposables += sneakerListInteractor.getSneakers(query)
                .map { viewModel.copy(items = it, loading = false) }
                .onErrorReturn { viewModel.copy(error = it, loading = false) }
                .startWith(viewModel.copy(loading = true, error = null, query = query))
                .subscribe { viewModel = it }
    }

    override fun onSneakerClick(position: Int) {
        view?.openSneakerDetail(viewModel.items[position].id)
    }

}