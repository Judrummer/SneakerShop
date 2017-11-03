package com.agoda.sneakershop

import com.agoda.sneakershop.di.DaggerTestAppComponent

class SneakerShopTestApplication : SneakerShopApplication() {

    override fun inject() {
        appComponent = DaggerTestAppComponent.builder().build()
    }

}
