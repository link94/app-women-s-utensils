package com.example.onlineshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ViewholderColorBinding

class ColorAdapter(val items: MutableList<String>):RecyclerView.Adapter<ColorAdapter.Viewholder>() {

    private var selectedPosition = -1
    private var lastSelectionPosition = -1
    private lateinit var context: Context
    inner class Viewholder(val binding: ViewholderColorBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        context = parent.context
        val binding = ViewholderColorBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(items[position])
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectionPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectionPosition)
            notifyItemChanged(selectedPosition)
        }

        if(selectedPosition == position){
            holder.binding.colorLayout.setBackgroundResource(R.drawable.white_bg_selected)
        }else{
            holder.binding.colorLayout.setBackgroundResource(R.drawable.white_bg)
        }
    }
}