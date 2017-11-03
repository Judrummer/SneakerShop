package com.agoda.sneakershop.screen

import android.view.View
import com.agoda.kakao.*
import com.agoda.sneakershop.R
import org.hamcrest.Matcher

class SneakerListScreen : Screen<SneakerListScreen>() {

    //TODO: searchView
    val searchIcon = KButton({ withId(R.id.ivSearch) })
    val sneakerList = KRecyclerView({ withId(R.id.rvSneakerList) }, { itemType(SneakerListScreen::Item) })

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val name: KTextView = KTextView(parent) { withId(R.id.tvItemSneakerName) }
        val price: KTextView = KTextView(parent) { withId(R.id.tvItemSneakerPrice) }
        val category: KTextView = KTextView(parent) { withId(R.id.tvItemSneakerCategory) }
        val collection: KTextView = KTextView(parent) { withId(R.id.tvItemSneakerCollection) }
        val image: KImageView = KImageView(parent) { withId(R.id.ivItemSneakerImage) }
    }

}