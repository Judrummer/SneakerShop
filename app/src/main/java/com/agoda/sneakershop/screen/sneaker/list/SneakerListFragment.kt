package com.agoda.sneakershop.screen.sneaker.list

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.agoda.sneakershop.R
import com.agoda.sneakershop.screen.base.BaseFragment
import com.agoda.sneakershop.screen.sneaker.list.adapter.SneakerListAdapter
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.fragment_sneaker_list.*
import javax.inject.Inject

class SneakerListFragment :
        BaseFragment<SneakerListViewModel, SneakerListContract.View, SneakerListContract.Presenter>(),
        SneakerListContract.View,
        SwipeRefreshLayout.OnRefreshListener,
        MaterialSearchView.OnQueryTextListener,
        MaterialSearchView.SearchViewListener, SneakerListAdapter.Listener,
        View.OnClickListener {

    interface Listener {
        fun openSneakerDetail(sneakerId: Long)
    }

    override val layoutId: Int = R.layout.fragment_sneaker_list

    @Inject
    lateinit var injectedPresenter: SneakerListContract.Presenter

    @Inject
    lateinit var sneakerListAdapter: SneakerListAdapter

    override fun createPresenter(): SneakerListContract.Presenter = injectedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sneakerListAdapter.listener = this

        rvSneakerList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sneakerListAdapter
        }

        srlSneakerList.setOnRefreshListener(this)
        ivSearch.setOnClickListener(this)
        searchView.setOnQueryTextListener(this)
        searchView.setOnSearchViewListener(this)

        if (savedInstanceState == null || presenter.currentViewModel.loading)
            presenter.fetchSneakers("")
    }

    override fun onClick(view: View) {
        if (view == ivSearch) searchView.showSearch()
    }

    override fun onItemClick(position: Int) {
        presenter.onSneakerClick(position)
    }

    override fun openSneakerDetail(id: Long) {
        if (activity is Listener) {
            (activity as Listener).openSneakerDetail(id)
        }
    }

    override fun onViewModelChanged(viewModel: SneakerListViewModel) {
        val (items, loading, error) = viewModel

        srlSneakerList.isRefreshing = loading

        if (error != null) {
            tvError.text = getString(R.string.sneaker_list_error_message, error.message ?: "")
            tvError.visibility = View.VISIBLE
        } else {
            tvError.visibility = View.GONE
        }

        sneakerListAdapter.items = items
    }

    override fun onBackPressed(): Boolean =
            if (searchView.isSearchOpen) {
                searchView.closeSearch()
                true
            } else {
                false
            }

    override fun onRefresh() {
        presenter.fetchSneakers(presenter.currentViewModel.query)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        presenter.fetchSneakers(query?.trim() ?: "")
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = false

    override fun onSearchViewClosed() {
        presenter.fetchSneakers("")
    }

    override fun onSearchViewShown() {
        //No-op
    }

}