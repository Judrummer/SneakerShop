package com.agoda.sneakershop.data.api

import com.agoda.sneakershop.data.entity.SneakerDetailEntity
import com.agoda.sneakershop.data.entity.SneakerEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SneakerApi {
    @GET("sneakers")
    fun getSneakers(@Query("textsearch") query: String): Single<List<SneakerEntity>>

    @GET("sneaker/{id}")
    fun getSneakerDetail(@Path("id") id: Long): Single<SneakerDetailEntity>
}