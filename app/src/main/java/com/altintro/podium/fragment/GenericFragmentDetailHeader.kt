package com.altintro.podium.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.altIntro.podium.R
import com.altintro.podium.model.Listable
import com.squareup.picasso.Picasso

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

    lateinit var fragmentView: View
    lateinit var image: ImageView
    lateinit var textTitle: TextView
    lateinit var textSubTitle: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if (inflater != null) {

            val content = arguments.getSerializable(ARG_CONTENT) as T

            fragmentView = inflater.inflate(R.layout.generic_fragment_detail_header, container, false)
            image = fragmentView.findViewById(R.id.image)
            textTitle = fragmentView.findViewById(R.id.text_title)
            textSubTitle = fragmentView.findViewById(R.id.text_sub_title)

            Picasso.get().load(content.get_Image()).placeholder(R.drawable.loading).into(image)

            textTitle.text = content.getTitle()
            textSubTitle.text = content.getSubtitle()
        }
        return fragmentView
    }
}