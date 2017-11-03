package com.agoda.sneakershop.data.repository

import com.agoda.sneakershop.data.api.SneakerApi
import com.agoda.sneakershop.data.entity.SneakerDetailEntity
import com.agoda.sneakershop.data.entity.SneakerEntity
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SneakerRepositoryImplTest {

    @Mock
    lateinit var sneakerApi: SneakerApi

    lateinit var sneakerRepository: SneakerRepository

    @Before
    fun setUp() {
        sneakerRepository = SneakerRepositoryImpl(sneakerApi)
    }

    @Test
    fun getSneakers() {
        val response = listOf<SneakerEntity>()
        val query = "query"
        whenever(sneakerApi.getSneakers(query)).thenReturn(Single.just(response))

        sneakerRepository.getSneakers(query).test().run {
            assertNoErrors()
            assertValue(response)
        }

        verify(sneakerApi).getSneakers(query)
    }

    @Test
    fun getSneakerDetail() {
        val response = SneakerDetailEntity()
        val id = 1L
        whenever(sneakerApi.getSneakerDetail(id)).thenReturn(Single.just(response))

        sneakerRepository.getSneakerDetail(id).test().run {
            assertNoErrors()
            assertValue(response)
        }

        verify(sneakerApi).getSneakerDetail(id)
    }

}