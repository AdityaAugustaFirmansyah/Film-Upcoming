package com.example.aditya.filmupcoming.presenter

import android.util.Log
import com.example.aditya.filmupcoming.model.DetailMovieResponse
import com.example.aditya.filmupcoming.model.ModelDetailMovie
import com.example.aditya.filmupcoming.model.ModelMovieResponse
import com.example.aditya.filmupcoming.network.ApiInterface
import com.example.aditya.filmupcoming.utils.SchedullerProvider
import com.example.aditya.filmupcoming.view.ViewPresenterDetailMovie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.ResourceSubscriber

class DetailMoviePresenter(
    private val apiInterface: ApiInterface,
    private val schedullerProvider: SchedullerProvider,
    private val viewDetail: ViewPresenterDetailMovie.ViewDetail
):ViewPresenterDetailMovie.DetailPresenter {
    private val compositeDisposable = CompositeDisposable()
    override fun loadData(id: String) {
        viewDetail.shoeLoading()
        val disposable:Disposable
        disposable = apiInterface.getMovieDetail(id)
            .observeOn(schedullerProvider.ui())
            .subscribeOn(schedullerProvider.io())
            .subscribeWith(object : ResourceSubscriber<DetailMovieResponse>(){
                override fun onComplete() {
                    viewDetail.hideLoading()
                }

                override fun onNext(t: DetailMovieResponse?) {
                    t?.results?.let { viewDetail.showData(it) }
                    viewDetail.hideLoading()
                }

                override fun onError(t: Throwable?) {
                    Log.d("ERROR","ERRRROR",t)
                    viewDetail.hideLoading()
                }

            })
        compositeDisposable.addAll(disposable)
    }
}