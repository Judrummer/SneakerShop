package com.agoda.sneakershop

import android.app.Application
import com.agoda.sneakershop.di.AppComponent
import com.agoda.sneakershop.di.DaggerAppComponent

open class SneakerShopApplication : Application() {

    lateinit var appComponent: AppComponent
        protected set

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    open protected fun inject() {
        appComponent = DaggerAppComponent.builder().build()
    }

}