package com.agoda.sneakershop.screen.sneaker.detail.mapper

import com.agoda.sneakershop.data.entity.SneakerDetailEntity
import com.agoda.sneakershop.screen.sneaker.detail.viewmodel.SneakerDetailContentViewModel

class SneakerDetailViewModelMapperImpl : SneakerDetailViewModelMapper {

    override fun map(sneakerDetailEntity: SneakerDetailEntity): SneakerDetailContentViewModel
            = SneakerDetailContentViewModel(
            id = sneakerDetailEntity.id,
            name = sneakerDetailEntity.name,
            categoryName = sneakerDetailEntity.categoryName,
            collectionName = sneakerDetailEntity.collectionName,
            price = sneakerDetailEntity.price,
            imageUrl = sneakerDetailEntity.imageUrl,
            description = sneakerDetailEntity.description ?: "")

}