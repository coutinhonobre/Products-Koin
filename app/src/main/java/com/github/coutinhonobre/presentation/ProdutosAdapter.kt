package com.github.coutinhonobre.presentation

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.github.coutinhonobre.R
import com.github.coutinhonobre.model.Product
import kotlinx.android.synthetic.main.spotlight_item.view.*

class ProdutosAdapter(var genericList: MutableList<Product>): RecyclerView.Adapter<ProdutosAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(product: Product){
            itemView.imageViewSpotlight.contentDescription = "${product.name} - ${product.description}"
            itemView.imageViewSpotlight.load(product.imageURL.toString()){
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
    )

    override fun getItemCount() = genericList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindView(genericList[position])
    }


}