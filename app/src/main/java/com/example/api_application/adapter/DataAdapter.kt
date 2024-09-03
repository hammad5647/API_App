package com.example.api_application.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.api_application.R
import com.example.api_application.databinding.SampleViewBinding
import com.example.api_application.models.PostModel

class DataAdapter(private var list: MutableList<PostModel>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bindings = SampleViewBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sample_view,parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.bindings.bodyTxt.text = list[position].body

    }
}