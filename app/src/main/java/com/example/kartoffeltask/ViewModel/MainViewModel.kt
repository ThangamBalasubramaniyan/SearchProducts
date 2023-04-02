package com.example.kartoffeltask.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kartoffeltask.APIs.Api
import com.example.kartoffeltask.APIs.RetrofitService
import com.example.kartoffeltask.DataModel.DataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    var myResponseList: MutableLiveData<ArrayList<DataItem>> = MutableLiveData()
    var apiInterface: Api = RetrofitService().getApiClient()!!.create(Api::class.java)

    fun getProducts() {
        apiInterface.getProducts().enqueue(object : Callback<ArrayList<DataItem>> {

            override fun onResponse(call: Call<ArrayList<DataItem>>, response: Response<ArrayList<DataItem>>) {
                myResponseList.value = response.body()
                println("Thangam ${myResponseList.value}")
            }
            override fun onFailure(call: Call<ArrayList<DataItem>>, t: Throwable) {
                Log.e("Thangam",t.message.toString())
            }
        })
    }
}