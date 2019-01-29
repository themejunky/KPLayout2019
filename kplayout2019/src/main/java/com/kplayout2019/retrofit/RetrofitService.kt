package com.kplayout2019.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    val interfaces: RetrofitInterface

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .client(client)
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