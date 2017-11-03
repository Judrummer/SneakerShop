package com.agoda.sneakershop.common.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun main(): Scheduler
    fun io(): Scheduler
}