package com.agoda.sneakershop

import android.support.v4.app.Fragment
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil

@Config(constants = BuildConfig::class,
        sdk = intArrayOf(21),
        packageName = "com.agoda.sneakershop",
        application = SneakerShopTestApplication::class)
@RunWith(RobolectricTestRunner::class)
abstract class BaseAppRobolectricTest {

    protected fun initialFragment(fragment: Fragment) {
        SupportFragmentTestUtil.startVisibleFragment(fragment)
    }

}