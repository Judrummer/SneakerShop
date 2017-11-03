package com.agoda.sneakershop

import android.support.test.runner.AndroidJUnitRunner
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins

class EspressoJUnitRunner : AndroidJUnitRunner() {
    override fun onStart() {
        RxJavaPlugins.setInitIoSchedulerHandler(Rx2Idler.create("RxJava 2 Io Scheduler"))
        super.onStart()
    }
}