package com.agoda.sneakershop.screen.base

import android.os.Bundle
import android.view.View
import com.agoda.sneakershop.BaseAppRobolectricTest
import com.agoda.sneakershop.extension.shouldEqual
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test

import org.parceler.Parcel
import org.parceler.ParcelConstructor
import org.parceler.Parcels

class BaseFragmentTest : BaseAppRobolectricTest() {

    lateinit var fragment: TestableBaseFragment

    @Before
    fun setUp() {
        fragment = TestableBaseFragment()
    }

    @Test
    fun getAppComponent() {
    }

    @Test
    fun onViewCreated_haveSavedState() {
        val view: View = mock()
        val viewModel = TestViewModel("Test")
        val bundle: Bundle = Bundle().apply {
            putParcelable(fragment.presenter::class.java.simpleName, Parcels.wrap(viewModel))
        }

        fragment.onViewCreated(view, bundle)

        verify(fragment.presenter).attachView(fragment)
        verify(fragment.presenter).restoreViewModel(viewModel)
    }

    @Test
    fun onViewCreated_noSavedState() {
        val view: View = mock()
        fragment.onViewCreated(view, null)

        verify(fragment.presenter).attachView(fragment)
        verify(fragment.presenter, never()).restoreViewModel(any())
    }

    @Test
    fun onDestroyView() {
        fragment.onDestroyView()

        verify(fragment.presenter).detachView()
    }

    @Test
    fun onSaveInstanceState() {
        val bundle = Bundle()
        val viewModel = TestViewModel("Test")
        whenever(fragment.presenter.currentViewModel).thenReturn(viewModel)

        fragment.onSaveInstanceState(bundle)

        val actual = Parcels.unwrap<TestViewModel>(bundle.getParcelable(fragment.presenter::class.java.simpleName))

        actual shouldEqual viewModel
    }

    @Test
    fun onBackPressed() {
        fragment.onBackPressed() shouldEqual false
    }

    @Parcel(Parcel.Serialization.BEAN)
    data class TestViewModel @ParcelConstructor constructor(val value: String)

    class TestableBaseFragment : BaseFragment<TestViewModel, BaseContract.View<TestViewModel>, BaseContract.Presenter<TestViewModel, BaseContract.View<TestViewModel>>>() {

        override fun onViewModelChanged(viewModel: TestViewModel) {

        }

        override val layoutId: Int = 1

        override fun createPresenter(): BaseContract.Presenter<TestViewModel, BaseContract.View<TestViewModel>> = mock()

    }

}