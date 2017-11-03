package com.agoda.sneakershop.data.di

import com.agoda.sneakershop.BuildConfig
import com.agoda.sneakershop.data.api.SneakerApi
import com.agoda.sneakershop.data.repository.SneakerRepository
import com.agoda.sneakershop.data.repository.SneakerRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }.build()

    @Provides
    @Named("ApiUrl")
    fun provideApiUrl(): String = "https://sneakerstoreapi.herokuapp.com/api/v1/"

    @Singleton
    @Provides
    fun provideRetrofit(@Named("ApiUrl") apiUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        baseUrl(apiUrl)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create())
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }.build()

    @Provides
    fun provideSneakerApi(retrofit: Retrofit): SneakerApi = retrofit.create(SneakerApi::class.java)

    //Repository
    @Provides
    fun provideSneakerRepository(sneakerApi: SneakerApi): SneakerRepository = SneakerRepositoryImpl(sneakerApi)
}