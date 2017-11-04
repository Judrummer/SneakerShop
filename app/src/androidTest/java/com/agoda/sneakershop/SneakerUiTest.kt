package com.agoda.sneakershop

import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.pressImeActionButton
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.agoda.sneakershop.ListScreen.Item
import com.agoda.sneakershop.screen.sneaker.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SneakerUiTest {
    @JvmField
    @Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    val screen = ListScreen()
    val details = DetailsScreen()

    @Test
    fun testDataLoading() {
        screen {
            recycler {
                firstChild<Item> {
                    name { hasText("Nike SF Air Force 1 Mid") }
                }
            }
        }
    }

    @Test
    fun testSearch() {
        fun testOriginalList() {
            screen {
                recycler {
                    firstChild<Item> {
                        name { hasText("Nike SF Air Force 1 Mid") }
                    }

                    lastChild<Item> {
                        name { hasText("JORDAN SUPER.FLY 2017") }
                    }
                }
            }
        }

        screen {
            testOriginalList()

            searchButton { click() }

            searchEdit {
                typeText("ZOOM")
                act { pressImeActionButton() }
                idle()
            }

            recycler {
                firstChild<Item> {
                    name { hasText("NIKE AIR ZOOM ELITE 9") }
                }

                lastChild<Item> {
                    name { hasText("NIKE AIR ZOOM VOMERO 12") }
                    price { hasText("$ 100") }
                }
            }

            searchBack {
                click()
                idle()
            }

            testOriginalList()
        }
    }

    @Test
    fun testDetails() {
        screen {
            recycler {
                childAt<Item>(3) { click() }
            }
        }

        details {
            name { hasText("NIKE AIR MAX 1 ULTRA FLYKNIT") }
            category { hasText("Lifestyle") }
            collection { hasText("Air Max 1") }
            price { hasText("$ 160") }

            pressBack()
        }

        screen {
            recycler { isDisplayed() }
        }
    }
}