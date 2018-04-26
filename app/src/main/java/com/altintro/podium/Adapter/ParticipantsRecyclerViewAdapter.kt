package com.altintro.podium.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.altintro.podium.R
import com.altintro.podium.model.User
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


class ParticipantsRecyclerViewAdapter(context: Context, items: List<User>) : RecyclerView.Adapter<ParticipantsRecyclerViewAdapter.ViewHolder>() {


    var mItems: List<User>
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
        val view = mInflater.inflate(R.layout.participant_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the view and textview in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems.get(position)

        Picasso.get().load(item.profilePic)
                .placeholder(R.drawable.loading).resize(100, 100).centerCrop()
                .into(holder.participantImgView)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var participantImgView:CircleImageView

        init {
            participantImgView = itemView.findViewById(R.id.participant_image_view)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, getAdapterPosition())
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): User {
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