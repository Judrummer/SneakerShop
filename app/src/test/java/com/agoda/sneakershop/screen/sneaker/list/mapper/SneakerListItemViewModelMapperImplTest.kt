package com.agoda.sneakershop.screen.sneaker.list.mapper

import com.agoda.sneakershop.data.entity.SneakerEntity
import com.agoda.sneakershop.extension.shouldEqual
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListItemViewModel
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SneakerListItemViewModelMapperImplTest {

    lateinit var mapper: SneakerListItemViewModelMapperImpl

    @Before
    fun setUp() {
        mapper = SneakerListItemViewModelMapperImpl()
    }

    @Test
    fun map() {
        //Arrange
        val entity = SneakerEntity(
                id = 1L,
                name = "AirMax",
                categoryId = 1L,
                categoryName = "category",
                collectionName = "collection",
                collectionId = 1L,
                price = 200.0,
                imageUrl = "www.google.com")

        //Action
        val viewModel = mapper.map(entity)

        //Assert
        viewModel shouldEqual SneakerListItemViewModel(
                id = 1L,
                name = "AirMax",
                categoryName = "category",
                collectionName = "collection",
                price = 200.0,
                imageUrl = "www.google.com"
        )
    }

}