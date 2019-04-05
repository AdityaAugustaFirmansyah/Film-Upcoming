package com.example.aditya.filmupcoming.network

import com.example.aditya.filmupcoming.BuildConfig.API_KEY
import retrofit2.Retrofit
import com.example.aditya.filmupcoming.BuildConfig.BASE_URL
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient{
    companion object {

    var outInstance:Retrofit? = null

    val instance:Retrofit
    get() {
        if (outInstance == null){
            outInstance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return outInstance!!
    }


    }
}