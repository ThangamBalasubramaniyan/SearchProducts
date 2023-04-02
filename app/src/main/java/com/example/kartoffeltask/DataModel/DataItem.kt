package com.example.kartoffeltask.DataModel

data class DataItem(
    val category: String,
    val description: String,
    val discount_price: Int,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
)