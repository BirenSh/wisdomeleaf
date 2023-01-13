package com.example.wishdomassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wishdomassignment.adapter.WisdomLeafAdapter
import com.example.wishdomassignment.databinding.ActivityMainBinding
import com.example.wishdomassignment.models.ModelData
import com.example.wishdomassignment.models.ModelDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    var dataList = arrayListOf<ModelDataItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //get the data from api
        getData()
        recyclerView = binding.recyclerView

        Log.d("sizeofdata",dataList.size.toString())

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
                    for (i in resultBody!!){
                        dataList.add(i)
                    }
                    Log.d("sizeofdata1",dataList.size.toString())
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    val adapter = WisdomLeafAdapter(dataList)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ModelData?>, t: Throwable) {
                Toast.makeText(this@MainActivity,"this Failed to load the data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}