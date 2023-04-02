package com.example.kartoffeltask.DetailProduct

import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kartoffeltask.R
import com.example.kartoffeltask.databinding.ActivityDetailProductBinding

class DetailProductActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getStringExtra("Image")
        val name = intent.getStringExtra("Name")
        val price = intent.getStringExtra("Price")
        val discountPrice = intent.getStringExtra("Discount Price")
        println("Thangam111 ${image}")

        Glide.with(this).load(image).into(binding.productImage)
        binding.productName.text = name
        binding.productPrice.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            text = getString(R.string.price, price)
        }
        binding.discountPrice.text = getString(R.string.price, discountPrice)
    }
}