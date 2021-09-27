package com.example.ilabank.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ilabank.R
import com.example.ilabank.model.ImageDataClass


class ViewPagerAdapter(
    private val context: Context,
    private val labelList: MutableList<ImageDataClass>?
) : RecyclerView.Adapter<ViewPagerAdapter.SlideDataHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideDataHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_slider, parent, false)
        return SlideDataHolder(v)
    }

    override fun onBindViewHolder(holder: SlideDataHolder, position: Int) {
        Glide.with(context)
            .load(labelList?.get(position)?.url)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return labelList?.size!!
    }


    inner class SlideDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.imageView)
        }
    }
}