package com.agoda.sneakershop.screen.sneaker.list.mapper

import com.agoda.sneakershop.data.entity.SneakerEntity
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListItemViewModel

class SneakerListItemViewModelMapperImpl : SneakerListItemViewModelMapper {

    override fun map(sneaker: SneakerEntity): SneakerListItemViewModel =
            SneakerListItemViewModel(
                    id = sneaker.id,
                    name = sneaker.name,
                    categoryName = sneaker.categoryName,
                    collectionName = sneaker.collectionName,
                    price = sneaker.price,
                    imageUrl = sneaker.imageUrl
            )

}