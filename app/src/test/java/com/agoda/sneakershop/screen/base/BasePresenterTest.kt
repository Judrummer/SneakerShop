package com.agoda.sneakershop.screen.base

import com.agoda.sneakershop.extension.shouldEqual
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BasePresenterTest {

    lateinit var presenter: TestableBasePresenter

    @Mock
    lateinit var view: BaseContract.View<String>

    @Before
    fun setup() {
        presenter = TestableBasePresenter()
        presenter.attachView(view)
    }

    @Test
    fun attachView() {
        verify(view).onViewModelChanged(presenter.defaultViewModel())
    }

    @Test
    fun currentViewModelIsDefaultViewModel() {
        presenter.currentViewModel shouldEqual presenter.defaultViewModel()
    }

    @Test
    fun setViewModel() {
        val expectedViewModel = "test"
        presenter.testableSetViewModel(expectedViewModel)

        val viewModelCaptor = argumentCaptor<String>()
        verify(view, times(2)).onViewModelChanged(viewModelCaptor.capture())

        viewModelCaptor.secondValue shouldEqual expectedViewModel
        presenter.currentViewModel shouldEqual expectedViewModel
    }

    @Test
    fun restoreViewModel() {
        val expectedViewModel = "test"
        presenter.restoreViewModel(expectedViewModel)

        val viewModelCaptor = argumentCaptor<String>()
        verify(view, times(2)).onViewModelChanged(viewModelCaptor.capture())

        viewModelCaptor.secondValue shouldEqual expectedViewModel
        presenter.currentViewModel shouldEqual expectedViewModel
    }

    @Test
    fun detachView() {
        presenter.detachView()

        presenter.testableGetView() shouldEqual null
    }

    class TestableBasePresenter : BasePresenter<String, BaseContract.View<String>>() {

        fun testableGetView() = this.view

        fun testableSetViewModel(viewModel: String) {
            this.viewModel = viewModel
        }

        override fun defaultViewModel(): String = "default"
    }

}