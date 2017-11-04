package com.agoda.sneakershop

import android.view.View
import android.widget.EditText
import com.agoda.kakao.*

import org.hamcrest.Matcher

class ListScreen : Screen<ListScreen>() {
    val searchButton = KButton { withId(R.id.ivSearch) }

    val searchEdit = KEditText {
        isDescendantOfA { withId(R.id.searchView) }
        isInstanceOf(EditText::class.java)
    }

    val searchBack = KButton {
        isDescendantOfA { withId(R.id.searchView) }
        withId(R.id.action_up_btn)
    }

    val recycler = KRecyclerView(
            { withId(R.id.rvSneakerList) },
            { itemType(::Item) }
    )

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val name = KTextView(parent) { withId(R.id.tvItemSneakerName) }
        val price = KTextView(parent) { withId(R.id.tvItemSneakerPrice) }
    }
}