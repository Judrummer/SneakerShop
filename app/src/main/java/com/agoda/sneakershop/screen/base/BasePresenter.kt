package com.agoda.sneakershop.screen.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<VM : Any, V : BaseContract.View<VM>> : BaseContract.Presenter<VM, V> {

    protected var view: V? = null

    protected var viewModel: VM = defaultViewModel()
        set(value) {
            field = value
            view?.onViewModelChanged(value)
        }

    protected val disposables = CompositeDisposable()

    abstract fun defaultViewModel(): VM

    override fun attachView(view: V) {
        this.view = view
        view.onViewModelChanged(viewModel)
    }

    override fun detachView() {
        this.view = null
        disposables.clear()
    }

    override fun restoreViewModel(viewModel: VM) {
        this.viewModel = viewModel
    }

    override val currentViewModel: VM
        get() = viewModel

}
