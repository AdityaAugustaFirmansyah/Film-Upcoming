package com.example.aditya.filmupcoming.network

import com.example.aditya.filmupcoming.BuildConfig.API_KEY
import com.example.aditya.filmupcoming.model.DetailMovieResponse
import com.example.aditya.filmupcoming.model.ModelMovieResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("movie/popular?api_key=$API_KEY")
    fun getMovie():Flowable<ModelMovieResponse>

    @GET("movie/{id}/reviews?api_key=$API_KEY")
    fun getMovieDetail(@Path("id")id:String):Flowable<DetailMovieResponse>
}