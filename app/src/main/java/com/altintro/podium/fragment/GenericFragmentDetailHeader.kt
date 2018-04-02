package com.altintro.podium.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.altintro.podium.OrientationMode
import com.altintro.podium.adapter.GenericRecyclerViewAdapter
import com.altintro.podium.model.Aggregate
import com.altintro.podium.model.Listable
import com.altintro.podium.model.User
import com.example.a630465.podium.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.generic_fragment_detail_header.*
import kotlinx.android.synthetic.main.generic_fragment_detail_header.view.*

class GenericFragmentDetailHeader <T: Listable>: Fragment() {
    companion object {
        val ARG_CONTENT = "ARG_CONTENT"

        fun <T: Listable> newInstance(content: T): GenericFragmentDetailHeader<T> {
            val fragment = GenericFragmentDetailHeader<T>()
            val arguments = Bundle()

            arguments.putSerializable(ARG_CONTENT, content)
            fragment.arguments = arguments

            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragmentView = super.onCreateView(inflater, container, savedInstanceState)
        val content = arguments.getSerializable(ARG_CONTENT) as T

        Picasso.with(activity).load(content.getImage()).placeholder(android.R.drawable.alert_dark_frame).into(image)
        text_title.text = content.getTitle()
        text_sub_title.text = content.getSubtitle()

        return fragmentView
    }
}