package com.altintro.podium.Adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.altintro.podium.model.HomeRecyclerViewItem
import com.example.a630465.podium.R
import java.util.*

class MyRecyclerViewAdapter(context: Context, items: List<HomeRecyclerViewItem>) : RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {


    var mItems: List<HomeRecyclerViewItem>
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null


    init {
        this.mInflater = LayoutInflater.from(context)
        this.mItems = items
    }

    override fun getItemCount(): Int {
        return mItems.count()
    }

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the view and textview in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems.get(position)
        holder.myView.setBackgroundColor(Color.GRAY)
        holder.myTextView.setText(item.name)
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var myView: View
        var myTextView: TextView

        init {
            myView = itemView.findViewById(R.id.itemView)
            myTextView = itemView.findViewById(R.id.itemName)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, getAdapterPosition())
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): HomeRecyclerViewItem {
        return mItems.get(id)
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener) {
        this.mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}