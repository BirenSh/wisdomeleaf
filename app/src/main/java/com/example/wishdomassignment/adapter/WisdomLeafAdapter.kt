package com.example.wishdomassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.wishdomassignment.R
import com.example.wishdomassignment.models.ModelDataItem

class WisdomLeafAdapter(val dataList: ArrayList<ModelDataItem>) :RecyclerView.Adapter<WisdomLeafAdapter.MyViewHolder>() {
    inner class MyViewHolder(view:View):RecyclerView.ViewHolder(view){
        val imageView:ImageView = view.findViewById(R.id.imageViewL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_layout,parent,false)
        return MyViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}