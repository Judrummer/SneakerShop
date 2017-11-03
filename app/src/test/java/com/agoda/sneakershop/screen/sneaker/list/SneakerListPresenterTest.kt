package com.agoda.sneakershop.screen.sneaker.list

import com.agoda.sneakershop.extension.shouldEqual
import com.agoda.sneakershop.screen.sneaker.list.interactor.SneakerListInteractor
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListItemViewModel
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListViewModel
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SneakerListPresenterTest {

    @Mock
    lateinit var sneakerListInteractor: SneakerListInteractor

    @Mock
    lateinit var view: SneakerListContract.View

    lateinit var presenter: SneakerListPresenter

    @Before
    fun setUp() {
        presenter = SneakerListPresenter(sneakerListInteractor)
        presenter.attachView(view)
    }

    @Test
    fun defaultViewModel() {
        presenter.defaultViewModel() shouldEqual SneakerListViewModel(listOf(), false, null, "")
    }

    @Test
    fun fetchSneakersSuccess() {
        val items = listOf(SneakerListItemViewModel(id = 0L, name = "", categoryName = "", collectionName = "", price = 0.0, imageUrl = ""))
        whenever(sneakerListInteractor.getSneakers("test")).thenReturn(Observable.just(items))

        presenter.fetchSneakers("test")

        val viewModelCaptor = argumentCaptor<SneakerListViewModel>()
        verify(view, times(3)).onViewModelChanged(viewModelCaptor.capture())

        viewModelCaptor.allValues[0] shouldEqual presenter.defaultViewModel()
        viewModelCaptor.allValues[1] shouldEqual SneakerListViewModel(listOf(), true, null, "test")
        viewModelCaptor.allValues[2] shouldEqual SneakerListViewModel(items, false, null, "test")
    }

    @Test
    fun fetchSneakersError() {
        val error = Throwable()
        whenever(sneakerListInteractor.getSneakers("")).thenReturn(Observable.error(error))

        presenter.fetchSneakers("")

        val viewModelCaptor = argumentCaptor<SneakerListViewModel>()
        verify(view, times(3)).onViewModelChanged(viewModelCaptor.capture())

        viewModelCaptor.allValues[0] shouldEqual presenter.defaultViewModel()
        viewModelCaptor.allValues[1] shouldEqual SneakerListViewModel(listOf(), true, null, "")
        viewModelCaptor.allValues[2] shouldEqual SneakerListViewModel(listOf(), false, error, "")
    }

    @Test
    fun onSneakerClick() {
        val viewModel = SneakerListItemViewModel(id = 10L, name = "", categoryName = "", collectionName = "", price = 0.0, imageUrl = "")

        presenter.restoreViewModel(SneakerListViewModel(listOf(viewModel), false, null, ""))
        presenter.onSneakerClick(0)

        verify(view).openSneakerDetail(viewModel.id)
    }

}