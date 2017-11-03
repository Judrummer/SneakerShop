package com.agoda.sneakershop.screen.sneaker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.agoda.sneakershop.R
import com.agoda.sneakershop.screen.base.BaseFragment
import com.bumptech.glide.manager.SupportRequestManagerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            //TODO: open SneakerListFragment
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
