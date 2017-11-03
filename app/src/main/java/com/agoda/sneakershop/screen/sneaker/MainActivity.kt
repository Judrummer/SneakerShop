package com.agoda.sneakershop.screen.sneaker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.agoda.sneakershop.R
import com.agoda.sneakershop.common.extension.transaction
import com.agoda.sneakershop.screen.base.BaseFragment
import com.agoda.sneakershop.screen.sneaker.detail.SneakerDetailFragment
import com.agoda.sneakershop.screen.sneaker.list.SneakerListFragment
import com.bumptech.glide.manager.SupportRequestManagerFragment


class MainActivity : AppCompatActivity(), SneakerListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                add(R.id.contentContainer, SneakerListFragment(), SneakerListFragment::class.java.simpleName)
            }
        }
    }

    override fun openSneakerDetail(sneakerId: Long) {
        supportFragmentManager.transaction {
            add(R.id.contentContainer, SneakerDetailFragment.instance(sneakerId), SneakerDetailFragment::class.java.simpleName)
            addToBackStack(null)
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.fragments.findLast {
            it !is SupportRequestManagerFragment && it is BaseFragment<*, *, *>
        } as? BaseFragment<*, *, *>

        val isHandleBackPressed = fragment?.onBackPressed() ?: false
        if (!isHandleBackPressed) super.onBackPressed()
    }

}
