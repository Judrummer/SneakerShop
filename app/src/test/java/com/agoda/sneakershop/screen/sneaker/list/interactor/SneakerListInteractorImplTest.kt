package com.agoda.sneakershop.screen.sneaker.list.interactor

import com.agoda.sneakershop.common.scheduler.SchedulerProvider
import com.agoda.sneakershop.data.entity.SneakerEntity
import com.agoda.sneakershop.data.repository.SneakerRepository
import com.agoda.sneakershop.extension.test
import com.agoda.sneakershop.screen.sneaker.list.mapper.SneakerListItemViewModelMapper
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListItemViewModel
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SneakerListInteractorImplTest {

    lateinit var interactor: SneakerListInteractorImpl

    @Mock
    lateinit var repository: SneakerRepository

    @Mock
    lateinit var schedulerProvider: SchedulerProvider

    @Mock
    lateinit var mapper: SneakerListItemViewModelMapper

    @Before
    fun setUp() {
        interactor = SneakerListInteractorImpl(repository, schedulerProvider, mapper)
    }

    @Test
    fun getSneakers() {
        //Arrange
        val query = "AirMax"
        val entity = SneakerEntity()
        val viewModel = SneakerListItemViewModel(
                id = 1L,
                name = "Sneaker",
                collectionName = "col",
                categoryName = "cat",
                imageUrl = "www.fofl",
                price = 0.0)

        whenever(repository.getSneakers(query)).thenReturn(Single.just(listOf(entity)))
        whenever(schedulerProvider.io()).thenReturn(Schedulers.trampoline())
        whenever(schedulerProvider.main()).thenReturn(Schedulers.trampoline())
        whenever(mapper.map(entity)).thenReturn(viewModel)

        //Action
        val testObserver = interactor.getSneakers(query).test()

        //Assert
        verify(repository).getSneakers(query)

        testObserver.run {
            assertValue(listOf(viewModel))
            assertNoErrors()
        }
    }

}