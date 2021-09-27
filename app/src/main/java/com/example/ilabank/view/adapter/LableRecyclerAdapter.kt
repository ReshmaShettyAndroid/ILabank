package com.example.ilabank.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ilabank.R
import java.util.*

class LableRecyclerAdapter(
    private val context: Context,
    private var dataList: MutableList<String>?
) : RecyclerView.Adapter<LableRecyclerAdapter.LabelViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_recycler_imagelabel, parent, false)
        return LabelViewHolder(v)
    }

    override fun onBindViewHolder(holder: LabelViewHolder, position: Int) {
        holder.txtLabel.setText(dataList?.get(position));
    }

    override fun getItemCount(): Int {
        return dataList?.size!!
    }

    fun filter(labelList: MutableList<String>?, query: String): MutableList<String> {
        var query = query.toLowerCase()
        val filteredDataList: MutableList<String> = ArrayList()
        if (labelList != null) {
            for (data in labelList) {
                val label = data.toLowerCase()
                if (label.contains(query)) {
                    filteredDataList.add(data)
                }
            }
        }
        this.dataList = filteredDataList
        notifyDataSetChanged()
        return filteredDataList
    }

    inner class LabelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtLabel: TextView
        init {
            txtLabel = itemView.findViewById(R.id.txtLabel)
        }
    }
}