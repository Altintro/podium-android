package com.altintro.podium.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.a630465.podium.R
import java.util.*

class MyRecyclerViewAdapter(context: Context, colors: List<Int>, animals: List<String>) : RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {


    var mViewColors: List<Int>
    var mAnimals: List<String>
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null


    init {
        this.mInflater = LayoutInflater.from(context)
        this.mViewColors = colors
        this.mAnimals = animals
    }

    override fun getItemCount(): Int {
        return mAnimals.count()
    }

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the view and textview in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val color = mViewColors.get(position)
        val animal = mAnimals.get(position)
        holder.myView.setBackgroundColor(color)
        holder.myTextView.setText(animal)
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var myView: View
        var myTextView: TextView

        init {
            myView = itemView.findViewById(R.id.colorView)
            myTextView = itemView.findViewById(R.id.tvAnimalName)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, getAdapterPosition())
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): String {
        return mAnimals.get(id)
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