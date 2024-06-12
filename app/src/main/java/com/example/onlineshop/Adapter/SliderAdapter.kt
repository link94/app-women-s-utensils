package com.example.onlineshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.onlineshop.Model.SliderModel
import com.example.onlineshop.R

class SliderAdapter(
    private var sliderItems: List<SliderModel>,
    private val viewPager2: ViewPager2
):RecyclerView.Adapter<SliderAdapter.SlideViewholder>() {
    private lateinit var context: Context
    private val runnable = Runnable{
        sliderItems = sliderItems
        notifyDataSetChanged()
    }
    class SlideViewholder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageSlide)

        fun setImage(sliderModel: SliderModel, context: Context){
            Glide.with(context)
                .load(sliderModel.url)
                .into(imageView)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.SlideViewholder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_item_container, parent, false)
        return SlideViewholder(view)
    }

    override fun onBindViewHolder(holder: SliderAdapter.SlideViewholder, position: Int) {
        holder.setImage(sliderItems[position], context)
        if(position == sliderItems.lastIndex-1){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int = sliderItems.size
}