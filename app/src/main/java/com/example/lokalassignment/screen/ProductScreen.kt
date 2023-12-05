package com.example.lokalassignment.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lokalassignment.R
import com.example.lokalassignment.adapter.ProductAdapter
import com.example.lokalassignment.api.RetrofitInstance
import com.example.lokalassignment.model.Product
import com.example.lokalassignment.model.ProductAPIResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductScreen : AppCompatActivity() {

    private lateinit var recView : RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_screen)

        recView = findViewById(R.id.recView)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        var products : ArrayList<Product> = arrayListOf()
        val productAdapter = ProductAdapter(this, products)
        recView.adapter = productAdapter
        recView.layoutManager = LinearLayoutManager(this)

        val retrofitInstance = RetrofitInstance.instance
        retrofitInstance.getProducts().enqueue(object : Callback<ProductAPIResponse> {
            override fun onResponse(
                call: Call<ProductAPIResponse>,
                response: Response<ProductAPIResponse>
            ) {
                val items = response.body()!!.products
                for(product in items)
                    products.add(product)
                productAdapter.notifyDataSetChanged()
                progressBar.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<ProductAPIResponse>, t: Throwable) {
                progressBar.visibility = View.INVISIBLE
            }

        })
    }
}