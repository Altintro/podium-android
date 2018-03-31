package com.altintro.podium.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.a630465.podium.R
import com.squareup.picasso.Picasso

class GenericVerticalRecyclerViewAdapter<T>(val content: T) : RecyclerView.Adapter<GenericVerticalRecyclerViewAdapter<T>.ContentViewHolder>() {

    var onClickListener : View.OnClickListener? = null
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.cell_generic, parent, false)
        view.setOnClickListener(onClickListener)
        context = parent!!.getContext()
        return ContentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return content.count()
    }

    override fun onBindViewHolder(holder: ContentViewHolder?, position: Int) {

        val contentImage= content.get(position).getEntityImage()
        val contentName = content.get(position).getEntityName()
        val contentAddress = content.get(position).getEntityAddress()

        holder?.bindShop(contentImage, contentName, contentAddress)
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val contentimage = itemView.findViewById<ImageView>(R.id.content_image)
        val contentName = itemView.findViewById<TextView>(R.id.content_name)
        val contentAddress = itemView.findViewById<TextView>(R.id.content_address)

        fun bindShop(image: String, name: String, address: String) {

            Picasso.with(context).load(image).placeholder(android.R.drawable.alert_dark_frame).into(contentimage)
            contentName.text = name
            contentAddress.text = address
        }
    }
}