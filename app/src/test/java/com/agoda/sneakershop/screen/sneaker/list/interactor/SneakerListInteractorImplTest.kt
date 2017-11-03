package com.agoda.sneakershop.screen.sneaker.list.interactor

import com.agoda.sneakershop.common.scheduler.SchedulerProvider
import com.agoda.sneakershop.data.entity.SneakerEntity
import com.agoda.sneakershop.data.repository.SneakerRepository
import com.agoda.sneakershop.screen.sneaker.list.mapper.SneakerListItemViewModelMapper
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListItemViewModel
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SneakerListInteractorImplTest {

    @Mock
    lateinit var sneakerRepository: SneakerRepository
    @Mock
    lateinit var viewModelMapper: SneakerListItemViewModelMapper
    @Mock
    lateinit var schedulerProvider: SchedulerProvider

    lateinit var interactor: SneakerListInteractor

    @Before
    fun setup() {
        whenever(schedulerProvider.main()).thenReturn(Schedulers.trampoline())
        whenever(schedulerProvider.io()).thenReturn(Schedulers.trampoline())
        interactor = SneakerListInteractorImpl(sneakerRepository, schedulerProvider, viewModelMapper)
    }

    @Test
    fun getSneakers() {
        val entity = SneakerEntity()
        val viewModel = SneakerListItemViewModel(0L, "", "", "", 0.0, "")

        whenever(viewModelMapper.map(entity)).thenReturn(viewModel)
        whenever(sneakerRepository.getSneakers("")).thenReturn(Single.just(listOf(entity)))

        interactor.getSneakers("").test().run {
            assertValue(listOf(viewModel))
            assertNoErrors()
        }

        verify(viewModelMapper).map(entity)
    }

}