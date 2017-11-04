package com.agoda.sneakershop.screen.sneaker.list

import com.agoda.sneakershop.common.extension.plusAssign
import com.agoda.sneakershop.screen.base.BasePresenter
import com.agoda.sneakershop.screen.sneaker.list.interactor.SneakerListInteractor
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListViewModel

class SneakerListPresenter(private val interactor: SneakerListInteractor)
    : BasePresenter<SneakerListViewModel, SneakerListContract.View>(),
        SneakerListContract.Presenter {

    override fun defaultViewModel(): SneakerListViewModel =
            SneakerListViewModel(
                    items = listOf(),
                    loading = false,
                    error = null,
                    query = "")

    override fun onSneakerClick(position: Int) {
        val id = viewModel.items[position].id
        view?.openSneakerDetail(id)
    }

    override fun fetchSneakers(query: String) {
//        viewModel = viewModel.copy(loading = true, query = query)
//
//        disposables += interactor.getSneakers(query).subscribe(
//                { success ->
//                    viewModel = viewModel.copy(
//                            loading = false,
//                            items = success,
//                            error = null)
//                },
//                { error ->
//                    viewModel = viewModel.copy(
//                            loading = false,
//                            items = listOf(),
//                            error = error
//                    )
//                })
        disposables += interactor.getSneakers(query)
                .map {
                    viewModel.copy(
                            loading = false,
                            items = it,
                            error = null)
                }
                .onErrorReturn {
                    viewModel.copy(
                            loading = false,
                            items = listOf(),
                            error = it
                    )
                }
                .startWith(viewModel.copy(
                        loading = true,
                        query = query
                ))
                .subscribe {
                    viewModel = it
                }

    }
}