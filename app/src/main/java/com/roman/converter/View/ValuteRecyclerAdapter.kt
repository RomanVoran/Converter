package com.roman.converter.View

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.roman.converter.Model.REST.Valute
import com.roman.converter.R
import com.roman.converter.ValuteList
import kotlinx.android.synthetic.main.item_valute.view.*

class ValuteRecyclerAdapter(val callback:(code:String)->Unit, val secondValute: String,val tappedValute: String):
    RecyclerView.Adapter<ValuteRecyclerAdapter.ValuteViewHolder>() {

    val dataList:ArrayList<Valute> = ArrayList()
    init {
        ValuteList.list.forEach {
            if (!it.charCode.equals(tappedValute))
                dataList.add(it)
        }
    }


    class ValuteViewHolder(itemView: View,val callback: (code: String) -> Unit,val dataList:ArrayList<Valute>): RecyclerView.ViewHolder(itemView) {
        var charCodeTextView: TextView? = null
        var nameTextView: TextView? = null
        var checkImage: ImageView? = null

        init{
            charCodeTextView = itemView.char_code
            nameTextView = itemView.valute_name
            checkImage = itemView.check_image
            itemView.setOnClickListener {
                //Log.e("MY_TAG","${dataList[adapterPosition].charCode}")
                callback(dataList[adapterPosition].charCode.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValuteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_valute,parent,false)
        return ValuteViewHolder(itemView,callback, dataList)
    }

    override fun onBindViewHolder(holder: ValuteViewHolder, position: Int) {
        holder.charCodeTextView?.text = dataList[position].charCode
        holder.nameTextView?.text = dataList[position].name
        holder.checkImage?.isVisible = dataList[position].charCode.equals(secondValute)

    }



    override fun getItemCount(): Int = dataList.size
}