package com.github.coutinhonobre.presentation

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.github.coutinhonobre.R
import com.github.coutinhonobre.model.Spotlight
import kotlinx.android.synthetic.main.spotlight_item.view.*

class SpotlightAdapter(var genericList: MutableList<Spotlight>): RecyclerView.Adapter<SpotlightAdapter.SpotlightViewHolder>() {

    class SpotlightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(spotlight: Spotlight){
            itemView.imageViewSpotlight.contentDescription = "${spotlight.name} - ${spotlight.description}"
            itemView.imageViewSpotlight.load(spotlight.bannerURL.toString()){
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
                itemView.imageViewSpotlight.scaleType = ImageView.ScaleType.FIT_XY
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SpotlightViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.spotlight_item, parent, false)
    )

    override fun getItemCount() = genericList.size

    override fun onBindViewHolder(holder: SpotlightViewHolder, position: Int) {
        holder.bindView(genericList[position])
    }


}