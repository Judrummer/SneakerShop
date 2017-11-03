package com.agoda.sneakershop.screen.sneaker.detail.mapper

import com.agoda.sneakershop.data.entity.SneakerDetailEntity
import com.agoda.sneakershop.screen.sneaker.detail.viewmodel.SneakerDetailContentViewModel

interface SneakerDetailViewModelMapper {
    fun map(sneakerDetailEntity: SneakerDetailEntity): SneakerDetailContentViewModel
}