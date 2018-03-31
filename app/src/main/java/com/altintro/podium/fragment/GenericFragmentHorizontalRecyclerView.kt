package com.altintro.podium.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a630465.podium.R
import java.io.Serializable

class GenericFragmentHorizontalRecyclerView <T: Serializable>: Fragment() {
    companion object {
        val ARG_CONTENT = "ARG_CONTENT"

        fun <T : Serializable> newInstance(content: T): GenericFragmentHorizontalRecyclerView<T> {
            val fragment = GenericFragmentHorizontalRecyclerView<T>()
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
            val content = arguments.getSerializable(ARG_CONTENT) // as
            val adapter = GenericHorizontalRecyclerViewAdapter(content)
            adapter.onClickListener = View.OnClickListener { view ->
                val position = recyclerViewContent.getChildAdapterPosition(view)

                // ToDo: get content and set action over this content

            }
            recyclerViewContent.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewContent.itemAnimator = DefaultItemAnimator()
            recyclerViewContent.adapter = adapter

        }

        return fragmentView


    }
}
