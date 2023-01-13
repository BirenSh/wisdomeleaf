package com.example.wishdomassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.wishdomassignment.models.ModelData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get the data from api
        getData()
    }
    private fun getData() {
        val retrofit = Retrofit.Builder().baseUrl("https://picsum.photos/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitApi = retrofit.create(ApiInterface::class.java)

        val call: Call<ModelData?>? = retrofitApi.getApiData()

        call?.enqueue(object : Callback<ModelData?> {
            override fun onResponse(call: Call<ModelData?>, response: Response<ModelData?>) {
                if(response.isSuccessful){
                    val resultBody = response.body()
                    Log.d("showData",resultBody.toString())
                }
            }

            override fun onFailure(call: Call<ModelData?>, t: Throwable) {
                Toast.makeText(this@MainActivity,"this Failed to load the data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}