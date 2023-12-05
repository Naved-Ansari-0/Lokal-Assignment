package com.example.lokalassignment.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.denzcoskun.imageslider.models.SlideModel
import com.example.lokalassignment.R
import com.example.lokalassignment.databinding.ProductDetailItemBinding
import com.example.lokalassignment.model.Product
import java.io.Serializable

class DetailScreen : AppCompatActivity() {

    private lateinit var binding: ProductDetailItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.product_detail_item)

        if(intent.hasExtra("product")){
            val product : Serializable? = intent.getSerializableExtra("product") as Product
            product as Product
            binding.apply{
                title.text = product.title
                originalPrice.text = product.price.toString()
                price.text = Math.round(product.price*(100-product.discountPercentage)/100.0).toString()
                discount.text = product.discountPercentage.toString()
                stock.text = product.stock.toString()
                rating.text = product.rating.toString()
                brand.text = product.brand
                description.text = product.description
                category.text = category.text
                val images = arrayListOf<SlideModel>()
                for(url in product.images)
                    images.add(SlideModel(url))
                imageSlider.setImageList(images)
            }
        }
    }
}