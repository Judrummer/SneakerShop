package com.agoda.sneakershop.screen.sneaker.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.agoda.sneakershop.R
import com.agoda.sneakershop.common.extension.format
import com.agoda.sneakershop.common.extension.setImageUrl
import com.agoda.sneakershop.screen.base.BaseFragment
import com.agoda.sneakershop.screen.sneaker.detail.viewmodel.SneakerDetailViewModel
import kotlinx.android.synthetic.main.fragment_sneaker_detail.*
import javax.inject.Inject

class SneakerDetailFragment : BaseFragment<SneakerDetailViewModel, SneakerDetailContract.View, SneakerDetailContract.Presenter>(), SneakerDetailContract.View {

    companion object {
        private const val ARG_SNEAKER_ID = "ARG_SNEAKER_ID"

        fun instance(id: Long) = SneakerDetailFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_SNEAKER_ID, id)
            }
        }
    }

    @Inject
    lateinit var injectedPresenter: SneakerDetailContract.Presenter

    val sneakerId by lazy { arguments?.getLong(ARG_SNEAKER_ID) ?: 0L }

    override val layoutId: Int = R.layout.fragment_sneaker_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        srlSneakerDetail.setOnRefreshListener { presenter.fetchSneakerDetail(sneakerId) }

        toolbar.navigationIcon = ContextCompat.getDrawable(context!!, R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        if (savedInstanceState == null || presenter.currentViewModel.loading) {
            presenter.fetchSneakerDetail(sneakerId)
        }
    }

    override fun createPresenter(): SneakerDetailContract.Presenter = injectedPresenter

    override fun onViewModelChanged(state: SneakerDetailViewModel) {
        val (content, loading, error) = state

        srlSneakerDetail.isRefreshing = loading

        if (error != null) {
            tvError.text = getString(R.string.sneaker_list_error_message, error.message ?: "")
            tvError.visibility = View.VISIBLE
        } else {
            tvError.visibility = View.GONE
        }

        if (error == null && content != null) {
            tvSneakerDetailName.text = content.name
            tvSneakerDetailCategoryName.text = content.categoryName
            tvSneakerDetailCollectiomName.text = content.collectionName
            tvSneakerDetailDescription.text = content.description
            tvSneakerDetailPrice.text = "$ ${content.price.format()}"
            ivSneakerDetailImage.setImageUrl(content.imageUrl)
            llContent.visibility = View.VISIBLE
        } else {
            llContent.visibility = View.GONE
        }

    }

}