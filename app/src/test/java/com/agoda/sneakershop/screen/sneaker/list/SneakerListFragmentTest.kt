package com.agoda.sneakershop.screen.sneaker.list

import android.view.View
import com.agoda.sneakershop.BaseAppRobolectricTest
import com.agoda.sneakershop.R
import com.agoda.sneakershop.extension.shouldEqual
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListItemViewModel
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListViewModel
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.android.synthetic.main.fragment_sneaker_list.*
import org.junit.Before
import org.junit.Test

class SneakerListFragmentTest : BaseAppRobolectricTest() {

    lateinit var fragment: SneakerListFragment

    @Before
    fun setup() {
        fragment = SneakerListFragment()
    }

    @Test
    fun onViewCreated() {
        initialFragment(fragment)

        verify(fragment.sneakerListAdapter).listener = fragment
        verify(fragment.presenter).fetchSneakers("")
    }

    @Test
    fun onBackPressed_Handle() {
        initialFragment(fragment)

        fragment.searchView.showSearch()

        val isHandleBackPressed = fragment.onBackPressed()

        isHandleBackPressed shouldEqual true
        fragment.searchView.isSearchOpen shouldEqual false
    }

    @Test
    fun onBackPressed_NotHandle() {
        initialFragment(fragment)

        val isHandleBackPressed = fragment.onBackPressed()

        isHandleBackPressed shouldEqual false
    }

    @Test
    fun onClick_ivSearch_showSearch() {
        initialFragment(fragment)

        fragment.onClick(fragment.ivSearch)

        fragment.searchView.isSearchOpen shouldEqual true
    }

    @Test
    fun onItemClick() {
        initialFragment(fragment)

        fragment.onItemClick(0)

        verify(fragment.presenter).onSneakerClick(0)
    }

    @Test
    fun onRefresh() {
        initialFragment(fragment)
        val viewModel = SneakerListViewModel(items = listOf(), error = null, loading = false, query = "query")
        whenever(fragment.presenter.currentViewModel).thenReturn(viewModel)

        fragment.onRefresh()

        verify(fragment.presenter).fetchSneakers(viewModel.query)
    }

    @Test
    fun onQueryTextSubmit() {
        initialFragment(fragment)
        val typeQuery = " nike airmax "
        val expectedQuery = "nike airmax"

        fragment.onQueryTextSubmit(typeQuery)

        verify(fragment.presenter).fetchSneakers(expectedQuery)
    }

    @Test
    fun onQueryTextChange() {
        initialFragment(fragment)

        val isHandle = fragment.onQueryTextChange("")

        isHandle shouldEqual false
    }

    @Test
    fun onSearchViewClosed() {
        initialFragment(fragment)

        fragment.onSearchViewClosed()

        verify(fragment.presenter, times(2)).fetchSneakers("") // first time when onViewCreated
    }

    @Test
    fun onSearchViewShown() {
        initialFragment(fragment)

        fragment.onSearchViewShown()

        //No-op
    }

    @Test
    fun onViewModelChanged_then_adapter_set_list() {
        initialFragment(fragment)
        val viewModel = SneakerListViewModel(
                items = listOf(SneakerListItemViewModel(0L, "", "", "", 0.0, "")),
                error = null, loading = true, query = "")

        fragment.onViewModelChanged(viewModel)

        verify(fragment.sneakerListAdapter).items = viewModel.items
    }

    @Test
    fun onViewModelChanged_isLoading_then_refreshing() {
        initialFragment(fragment)
        val viewModel = SneakerListViewModel(items = listOf(), error = null, loading = true, query = "")

        fragment.onViewModelChanged(viewModel)

        fragment.srlSneakerList.isRefreshing shouldEqual true

    }

    @Test
    fun onViewModelChanged_isNotLoading_then_notRefreshing() {
        initialFragment(fragment)
        val viewModel = SneakerListViewModel(items = listOf(), error = null, loading = false, query = "")

        fragment.onViewModelChanged(viewModel)

        fragment.srlSneakerList.isRefreshing shouldEqual false
    }

    @Test
    fun onViewModelChanged_containError_then_showError_hideList() {
        initialFragment(fragment)
        val errorMessage = "Message"
        val viewModel = SneakerListViewModel(items = listOf(), error = Throwable(errorMessage), loading = false, query = "")
        val expectedMessage = fragment.context?.getString(R.string.sneaker_list_error_message, errorMessage)
        fragment.onViewModelChanged(viewModel)

        fragment.tvError.text shouldEqual expectedMessage
        fragment.tvError.visibility shouldEqual View.VISIBLE
    }

    @Test
    fun onViewModelChanged_noError_then_hideError_showList() {
        initialFragment(fragment)
        val viewModel = SneakerListViewModel(items = listOf(), error = null, loading = false, query = "")

        fragment.onViewModelChanged(viewModel)

        fragment.tvError.visibility shouldEqual View.GONE
    }

}