package com.example.aditya.filmupcoming.view

import com.example.aditya.filmupcoming.model.ModelDetailMovie

interface ViewPresenterDetailMovie {
    interface ViewDetail {

        fun shoeLoading()
        fun hideLoading()
        fun showData(list: List<ModelDetailMovie>)
    }

    interface DetailPresenter{
        fun loadData(id: String)
    }
}