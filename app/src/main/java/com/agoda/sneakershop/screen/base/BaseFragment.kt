package com.agoda.sneakershop.screen.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agoda.sneakershop.SneakerShopApplication
import com.agoda.sneakershop.di.AppComponent
import org.parceler.Parcels

abstract class BaseFragment<VM : Any, in V : BaseContract.View<VM>, out P : BaseContract.Presenter<VM, V>> : Fragment(), BaseContract.View<VM> {

    abstract val layoutId: Int

    val presenter: P by lazy { createPresenter() }
    val appComponent: AppComponent get() = (context!!.applicationContext as SneakerShopApplication).appComponent

    abstract fun createPresenter(): P

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(layoutId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this as V)
        savedInstanceState?.let {
            presenter.restoreViewModel(Parcels.unwrap(it.getParcelable(presenter::class.java.simpleName)))
        }
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(presenter::class.java.simpleName, Parcels.wrap(presenter.currentViewModel))
    }

    open fun onBackPressed(): Boolean = false

}