package com.agoda.sneakershop.screen.sneaker.list.mapper

import com.agoda.sneakershop.data.entity.SneakerEntity
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListItemViewModel
import com.agoda.sneakershop.extension.shouldEqual
import org.junit.Before
import org.junit.Test

class SneakerListItemViewModelMapperImplTest {

    lateinit var sneakerListItemViewModelMapper: SneakerListItemViewModelMapper

    @Before
    fun setup() {
        sneakerListItemViewModelMapper = SneakerListItemViewModelMapperImpl()
    }

    @Test
    fun map() {
        val sneaker = SneakerEntity(id = 0, name = "name0", categoryId = 0, categoryName = "category0",
                collectionId = 0, collectionName = "collection0", price = 100.0, imageUrl = "image0")

        val expectedItemViewModel = SneakerListItemViewModel(id = 0, name = "name0", categoryName = "category0",
                collectionName = "collection0", price = 100.0, imageUrl = "image0")

        sneakerListItemViewModelMapper.map(sneaker) shouldEqual expectedItemViewModel
    }
}