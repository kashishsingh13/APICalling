package com.example.retrofitapplication

import com.example.retrofitapplication.Model.City
import com.example.retrofitapplication.Model.Country
import com.example.retrofitapplication.Model.State
import com.example.retrofitapplication.Model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun getUserList(@Query("page") p:Int): Call<UserResponse>

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJfZW1haWwiOiJrYXNoaXNoQGdtYWlsLmNvbSIsImFwaV90b2tlbiI6InJGdWJrdFplQ2pQQ1l2Um5fS1g3X3c1bGtEV3BhTGZxellrdnZKSXUzdGwxZ2RfQmhIUmlMM3hTSGF5bDMzeVo3RUUifSwiZXhwIjoxNzAxMzU3NjQ1fQ.fSzmoJeWo7f7MIkXt7GgrwvwIpLQzAtW7kA2fPXg5Dc",
        "Accept: application/json"
    )
    @GET("countries")
    fun getCountries(): Call<MutableList<Country>>

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJfZW1haWwiOiJrYXNoaXNoQGdtYWlsLmNvbSIsImFwaV90b2tlbiI6InJGdWJrdFplQ2pQQ1l2Um5fS1g3X3c1bGtEV3BhTGZxellrdnZKSXUzdGwxZ2RfQmhIUmlMM3hTSGF5bDMzeVo3RUUifSwiZXhwIjoxNzAxMzU3NjQ1fQ.fSzmoJeWo7f7MIkXt7GgrwvwIpLQzAtW7kA2fPXg5Dc",
        "Accept: application/json"
    )
    @GET("states/{con}")
    fun getState(@Path("con") country: String): Call<MutableList<State>>

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJfZW1haWwiOiJrYXNoaXNoQGdtYWlsLmNvbSIsImFwaV90b2tlbiI6InJGdWJrdFplQ2pQQ1l2Um5fS1g3X3c1bGtEV3BhTGZxellrdnZKSXUzdGwxZ2RfQmhIUmlMM3hTSGF5bDMzeVo3RUUifSwiZXhwIjoxNzAxMzU3NjQ1fQ.fSzmoJeWo7f7MIkXt7GgrwvwIpLQzAtW7kA2fPXg5Dc",
        "Accept: application/json"
    )
    @GET("cities/{sta}")
    fun getCity(@Path("sta") state: String): Call<MutableList<City>>
}