package com.example.kartoffeltask.APIs

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    var BASE_URL: String = "https://my-project-6e139-default-rtdb.firebaseio.com/"
    var retrofit: Retrofit? = null

    fun getApiClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }
}