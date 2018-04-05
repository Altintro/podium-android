package com.altintro.podium.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.altIntro.podium.R
import com.altintro.podium.OrientationMode
import com.altintro.podium.adapter.GenericRecyclerViewAdapter
import com.altintro.podium.model.Aggregate
import com.altintro.podium.model.Listable
import java.io.Serializable

class GenericFragmentHorizontalRecyclerView <Z: Listable, T : Aggregate<Z>>: Fragment() {
    companion object {
        val ARG_CONTENT = "ARG_CONTENT"

        fun <Z: Listable, T : Aggregate<Z>> newInstance(content: T?): GenericFragmentHorizontalRecyclerView<Z, T> {
            val fragment = GenericFragmentHorizontalRecyclerView<Z, T>()
            val arguments = Bundle()

            arguments.putSerializable(ARG_CONTENT, content)
            fragment.arguments = arguments

            return fragment
        }

    }

    lateinit var fragmentView: View
    lateinit var recyclerViewContent: RecyclerView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if (inflater != null) {
            fragmentView = inflater.inflate(R.layout.generic_fragment_horizontal_recycler_view, container, false)
            recyclerViewContent = fragmentView.findViewById(R.id.generic_horizontal_recycler_view)
            val content = arguments.getSerializable(GenericFragmentHorizontalRecyclerView.ARG_CONTENT) as T
            val adapter = GenericRecyclerViewAdapter<Z,T>(content, OrientationMode.HORIZONTAL)
            adapter.onClickListener = View.OnClickListener { view ->
                val position = recyclerViewContent.getChildAdapterPosition(view)

                // ToDo: get content and set action when user push over this content

            }
            recyclerViewContent.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewContent.itemAnimator = DefaultItemAnimator()
            recyclerViewContent.adapter = adapter

        }

        return fragmentView


    }
}
