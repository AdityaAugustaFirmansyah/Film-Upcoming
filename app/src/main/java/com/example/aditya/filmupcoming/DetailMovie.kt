package com.example.aditya.filmupcoming

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.aditya.filmupcoming.BuildConfig.URL_POSTER
import com.example.aditya.filmupcoming.model.ModelDetailMovie
import com.example.aditya.filmupcoming.network.ApiClient
import com.example.aditya.filmupcoming.network.ApiInterface
import com.example.aditya.filmupcoming.presenter.DetailMoviePresenter
import com.example.aditya.filmupcoming.utils.AppSchedullerProvider
import com.example.aditya.filmupcoming.view.ViewPresenterDetailMovie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovie : AppCompatActivity(),ViewPresenterDetailMovie.ViewDetail {

    private lateinit var presenter:DetailMoviePresenter
    private lateinit var detail:ModelDetailMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        val cek = intent.getIntExtra("id",0)
        val cekImage = intent.getStringExtra("gambar")
        val tiitle = intent.getStringExtra("judul")


        text_detail.text = tiitle
        Picasso.get().load(URL_POSTER+cekImage).into(image_detail)
        val request = ApiClient.instance.create(ApiInterface::class.java)
        presenter = DetailMoviePresenter(request,AppSchedullerProvider(),this)
        presenter.loadData(cek.toString())

    }
    override fun shoeLoading() {
        loading_detail.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading_detail.visibility = View.INVISIBLE
    }

    override fun showData(list: List<ModelDetailMovie>) {
        detail = ModelDetailMovie(list[0].author,list[0].content)

    }
}
