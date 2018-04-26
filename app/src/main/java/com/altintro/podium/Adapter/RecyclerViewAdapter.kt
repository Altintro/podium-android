package com.altintro.podium.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.altintro.podium.OrientationMode
import com.altintro.podium.R
import com.altintro.podium.model.Aggregate
import com.altintro.podium.model.Listable
import com.squareup.picasso.Picasso


class RecyclerViewAdapter<Z: Listable, T : Aggregate<Z>>(val content: T, val orientation: OrientationMode) : RecyclerView.Adapter<RecyclerViewAdapter<Z, T>.ContentViewHolder>() {

    private var onClickListener : ItemClickListener? = null
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ContentViewHolder {
        val cellLayout = if (orientation == OrientationMode.VERTICAL) {
            R.layout.generic_cell_vertical} else {R.layout.generic_cell_horizontal}
        val view = LayoutInflater.from(parent?.context).inflate(cellLayout, parent, false)
        context = parent!!.getContext()
        return ContentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return content.count()
    }

    override fun onBindViewHolder(holder: ContentViewHolder?, position: Int) {

        val contentImage= content.get(position).get_Image()
        val contentTitle = content.get(position).getTitle()
        val contentSubTitle = if (orientation == OrientationMode.VERTICAL) { content.get(position).getSubtitle() } else {""}

        holder?.bindShop(contentImage, contentTitle, contentSubTitle)
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val contentImage = itemView.findViewById<ImageView>(R.id.content_image)
        val contentTitle = itemView.findViewById<TextView>(R.id.content_title)
        val contentSubtitle = itemView.findViewById<TextView>(R.id.content_subtitle)


        fun bindShop(image: String, title: String, subTitle: String) {

            // Si la imagen  no es vacía la muestro, si lo es quito el ImageView de la vista
            if (image != "") {
                Picasso.get().load(image).placeholder(R.drawable.loading).into(contentImage)

            } else {
                contentImage.visibility = View.GONE
            }
            contentTitle.text = title
            if (subTitle != "") { contentSubtitle.text = subTitle }
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if(onClickListener != null) onClickListener!!.onItemClick(view, getAdapterPosition(), contentTitle.text.toString())
        }
    }

    fun setClickListener(sportClickListener: ItemClickListener) {
        this.onClickListener = sportClickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int, content: String)
    }
}