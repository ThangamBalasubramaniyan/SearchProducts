package com.example.kartoffeltask.APIs

import com.example.kartoffeltask.DataModel.DataItem
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("data.json")
    fun getProducts() : Call<ArrayList<DataItem>>
}