package com.example.retrofitapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitapplication.Model.User
import com.example.retrofitapplication.Model.UserResponse
import com.example.retrofitapplication.Network.ApiClient
import com.example.retrofitapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    private lateinit var userAdapter: UserAdapter
   private var userlist= mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userAdapter = UserAdapter(this, userlist, binding.total) // You can pass an empty list initially
        binding.recyleview.adapter = userAdapter
        binding.recyleview.layoutManager = LinearLayoutManager(this)

        retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService=retrofit.create(ApiService::class.java)
        fetchUserList()
    }

    private fun fetchUserList() {
       apiService.getUserList(1).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                // success

                if(response.isSuccessful){
                    val userList = response.body()?.userList
                    userList?.let {
                        binding.total.text="Total User : ${userList.size.toString()}"
                        userAdapter.userList = it
                        userAdapter.notifyDataSetChanged()

                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {

            }

        })

    }
}