package com.example.wishdomassignment.adapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wishdomassignment.ui.MainActivity
import com.example.wishdomassignment.R
import com.example.wishdomassignment.models.ModelDataItem
import com.squareup.picasso.Picasso

class WisdomLeafAdapter(val dataList: ArrayList<ModelDataItem>, val context: MainActivity) :RecyclerView.Adapter<WisdomLeafAdapter.MyViewHolder>() {
    inner class MyViewHolder(view:View):RecyclerView.ViewHolder(view){
        val imageView:ImageView = view.findViewById(R.id.imageViewL)
        val imageTitle:TextView = view.findViewById(R.id.imageTitle)
        val imageDesc:TextView = view.findViewById(R.id.imageDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_layout,parent,false)
        return MyViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataPos = dataList[position]
        Picasso.get().load(dataPos.download_url).into(holder.imageView)
        holder.imageTitle.text = dataPos.author
//        holder.imageTitle.text = dataPos.author
        holder.imageView.setOnClickListener {
            showDialog(dataPos)
        }
    }

    private fun showDialog(dataPos: ModelDataItem) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.setCancelable(true)
        val authorName = dialog.findViewById(R.id.authorTextDB) as TextView
        authorName.text = dataPos.author
        val imageSize = dialog.findViewById<TextView>(R.id.imageSizeDB)
        imageSize.text = "${dataPos.width} X ${dataPos.height}"
        val imageUrl:TextView = dialog.findViewById(R.id.imageLinkDB)
        imageUrl.text = dataPos.url
        dialog.create()
        dialog.show()
    }


    override fun getItemCount(): Int {
        return dataList.size
    }
}