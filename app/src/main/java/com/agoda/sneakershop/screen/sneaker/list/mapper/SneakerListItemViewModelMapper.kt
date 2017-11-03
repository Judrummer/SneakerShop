package com.agoda.sneakershop.screen.sneaker.list.mapper

import com.agoda.sneakershop.data.entity.SneakerEntity
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListItemViewModel

interface SneakerListItemViewModelMapper {
    fun map(sneaker: SneakerEntity): SneakerListItemViewModel
}