package com.agoda.sneakershop

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.agoda.sneakershop.screen.SneakerListScreen
import com.agoda.sneakershop.screen.sneaker.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SneakerShopTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    private val screen = SneakerListScreen()

    @Test
    fun testLoadData() {
        screen {
            sneakerList {
                isDisplayed()

                firstChild<SneakerListScreen.Item> {
                    name {
                        isDisplayed()
                        hasText("Nike SF Air Force 1 Mid")
                    }
                }
            }
        }
    }

}