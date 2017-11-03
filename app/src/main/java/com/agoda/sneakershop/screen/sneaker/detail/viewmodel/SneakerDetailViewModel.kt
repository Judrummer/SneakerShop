package com.agoda.sneakershop.screen.sneaker.detail.viewmodel

import org.parceler.Parcel
import org.parceler.ParcelConstructor


@Parcel(Parcel.Serialization.BEAN)
data class SneakerDetailViewModel @ParcelConstructor constructor(
        val content: SneakerDetailContentViewModel?,
        val loading: Boolean,
        val error: Throwable?)

@Parcel(Parcel.Serialization.BEAN)
data class SneakerDetailContentViewModel @ParcelConstructor constructor(
        val id: Long,
        val name: String,
        val categoryName: String,
        val collectionName: String,
        val price: Double,
        val imageUrl: String,
        val description: String)