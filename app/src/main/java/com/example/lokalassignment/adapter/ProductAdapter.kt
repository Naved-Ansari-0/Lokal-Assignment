package com.example.lokalassignment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lokalassignment.R
import com.example.lokalassignment.model.Product
import com.example.lokalassignment.screen.DetailScreen

class ProductAdapter(
    private val context: Context,
    private val products : List<Product>
):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val cardView : CardView = itemView.findViewById(R.id.cardView)
        val imageView : ImageView = itemView.findViewById(R.id.imageView)
        val title : TextView = itemView.findViewById(R.id.title)
        val originalPrice : TextView = itemView.findViewById(R.id.originalPrice)
        val price : TextView = itemView.findViewById(R.id.price)
        val discount : TextView = itemView.findViewById(R.id.discount)
        val rating : TextView = itemView.findViewById(R.id.rating)
        val description : TextView = itemView.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.apply {
            title.text = product.title
            originalPrice.text = product.price.toString()
            price.text = (Math.round(product.price*(100-product.discountPercentage))/100.0).toString()
            discount.text = product.discountPercentage.toString()
            rating.text = product.rating.toString()
            description.text = product.description
            Glide.with(context).load(product.thumbnail).into(imageView)
            cardView.setOnClickListener {
                val intent : Intent = Intent(context, DetailScreen::class.java)
                intent.putExtra("product", product)
                context.startActivity(intent)
            }
        }
    }
}