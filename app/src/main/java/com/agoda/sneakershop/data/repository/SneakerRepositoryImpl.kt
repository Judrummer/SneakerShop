package com.agoda.sneakershop.data.repository

import com.agoda.sneakershop.data.api.SneakerApi
import com.agoda.sneakershop.data.entity.SneakerDetailEntity
import com.agoda.sneakershop.data.entity.SneakerEntity
import io.reactivex.Single

class SneakerRepositoryImpl(private val sneakerApi: SneakerApi) : SneakerRepository {

    override fun getSneakers(query: String): Single<List<SneakerEntity>> = sneakerApi.getSneakers(query)

    override fun getSneakerDetail(id: Long): Single<SneakerDetailEntity> = sneakerApi.getSneakerDetail(id)

}