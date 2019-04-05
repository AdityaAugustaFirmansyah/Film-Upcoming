package com.example.aditya.filmupcoming.presenter

import android.util.Log
import com.example.aditya.filmupcoming.model.ModelMovieResponse
import com.example.aditya.filmupcoming.network.ApiInterface
import com.example.aditya.filmupcoming.utils.SchedullerProvider
import com.example.aditya.filmupcoming.view.ViewPresenterMovie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.ResourceSubscriber

class MoviePresenter(var mainView: ViewPresenterMovie.MainViewMovie,
                     private val request:ApiInterface,
                     private val schedullerProvider: SchedullerProvider)
    :ViewPresenterMovie.MainPresenterMovie {

    private val compositeDisposable = CompositeDisposable()

    override fun loadData() {
        mainView.showLoading()
        val disposable:Disposable
        disposable = request.getMovie()
            .observeOn(schedullerProvider.ui())
            .subscribeOn(schedullerProvider.io())
            .subscribeWith(object : ResourceSubscriber<ModelMovieResponse>(){
                override fun onComplete() {
                    mainView.hideLoading()
                }

                override fun onNext(t: ModelMovieResponse?) {
                    t?.results?.let { mainView.showData(it) }
                    mainView.hideLoading()
                }

                override fun onError(t: Throwable?) {
                    Log.d("ERROR","ERRRROR",t)
                    mainView.hideLoading()
                }

            })
        compositeDisposable.addAll(disposable)
    }

}