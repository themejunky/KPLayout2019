package com.kplayout2019.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    val interfaces: RetrofitInterface

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://mobdash.crave.ro")
            .build()

        interfaces = retrofit.create(RetrofitInterface::class.java)
    }

    private var instance: RetrofitService? = null

    @Synchronized
    fun getInstance(): RetrofitService {
        if (instance != null) {
            instance
        } else {
            instance = RetrofitService()
        }
        return instance as RetrofitService
    }
}