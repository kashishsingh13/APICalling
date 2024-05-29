package com.example.retrofitapplication

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.retrofitapplication.Model.City
import com.example.retrofitapplication.Model.Country
import com.example.retrofitapplication.Model.State
import com.example.retrofitapplication.Network.ApiClient
import com.example.retrofitapplication.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadCountry()

    }

    private fun loadCountry() {
        ApiClient.init().getCountries().enqueue(object : Callback<MutableList<Country>> {
            override fun onResponse(
                call: Call<MutableList<Country>>,
                response: Response<MutableList<Country>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        var countryAdapter = ArrayAdapter(
                            applicationContext,
                            android.R.layout.simple_spinner_dropdown_item,
                            it
                        )
                        binding.autoCountry.setAdapter(countryAdapter)

                        binding.autoCountry.onItemClickListener = object : OnItemClickListener {
                            override fun onItemClick(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                var country = parent?.getItemAtPosition(position) as Country
//                              Toast.makeText(applicationContext,"$country", Toast.LENGTH_SHORT).show()
                                getStateList(country.name)
                            }

                        }
                    }
                }
            }
            override fun onFailure(call: Call<MutableList<Country>>, t: Throwable) {
                Log.d("TAG", "onFailure: ")

            }
        })
    }

    private fun getStateList(country: String) {
        binding.autoState.text.clear()
        binding.autoCity.text.clear()
        ApiClient.init().getState(country).enqueue(object : Callback<MutableList<State>> {
            override fun onResponse(
                call: Call<MutableList<State>>,
                response: Response<MutableList<State>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        var stateAdapter = ArrayAdapter(
                            applicationContext,
                            android.R.layout.simple_spinner_dropdown_item,
                            it
                        )
                        binding.autoState.setAdapter(stateAdapter)
                        binding.autoState.onItemClickListener = object : OnItemClickListener {
                            override fun onItemClick(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                var state = parent?.getItemAtPosition(position) as State
//                                Toast.makeText(applicationContext, "$state", Toast.LENGTH_SHORT).show()
                                getCityList(state.name)
                            }
                        }
                    }
                }

            }
            override fun onFailure(call: Call<MutableList<State>>, t: Throwable) {
                Log.d("TAG", "onFailure: ")
            }
        })
    }

    private fun getCityList(state: String) {
        ApiClient.init().getCity(state).enqueue(object : Callback<MutableList<City>> {
            override fun onResponse(
                call: Call<MutableList<City>>,
                response: Response<MutableList<City>>
            ) {
                if (response.isSuccessful) {

                    response.body()?.let {
                        var cityAdapter = ArrayAdapter(
                            applicationContext,
                            android.R.layout.simple_spinner_dropdown_item,
                            it
                        )
                        binding.autoCity.setAdapter(cityAdapter)

//                        binding.autoCity.onItemClickListener = object : OnItemClickListener {
//                            override fun onItemClick(
//                                parent: AdapterView<*>?,
//                                view: View?,
//                                position: Int,
//                                id: Long
//                            ) {
//                                var city = parent?.getItemAtPosition(position) as City
////                                Toast.makeText(applicationContext, "$state", Toast.LENGTH_SHORT).show()
//                                getCityList(city.name)
//                            }
//                        }
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<City>>, t: Throwable) {
                Log.d("TAG", "onFailure: ")
            }
        })
    }
}