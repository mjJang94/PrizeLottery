package com.mj.prizelottery.config.net

import com.google.gson.GsonBuilder
import com.mj.prizelottery.config.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private var instance: Retrofit ?= null
    private val gson = GsonBuilder().setLenient().create()

    fun getInstance(): Retrofit{
        if (instance == null){
            instance = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return instance!!
    }
}