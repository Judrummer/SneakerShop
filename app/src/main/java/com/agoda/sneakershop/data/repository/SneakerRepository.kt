package com.agoda.sneakershop.data.repository

import com.agoda.sneakershop.data.entity.SneakerDetailEntity
import com.agoda.sneakershop.data.entity.SneakerEntity
import io.reactivex.Single

interface SneakerRepository {
    fun getSneakers(query: String): Single<List<SneakerEntity>>
    fun getSneakerDetail(id: Long): Single<SneakerDetailEntity>
}
