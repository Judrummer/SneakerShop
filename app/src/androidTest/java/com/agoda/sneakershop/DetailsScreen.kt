package com.agoda.sneakershop

import com.agoda.kakao.KTextView
import com.agoda.kakao.Screen

class DetailsScreen : Screen<DetailsScreen>() {
    val name = KTextView { withId(R.id.tvSneakerDetailName) }
    val category = KTextView { withId(R.id.tvSneakerDetailCategoryName) }
    val collection = KTextView { withId(R.id.tvSneakerDetailCollectiomName) }
    val price = KTextView { withId(R.id.tvSneakerDetailPrice) }
}