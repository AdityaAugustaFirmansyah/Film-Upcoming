package com.example.aditya.filmupcoming.view

import com.example.aditya.filmupcoming.model.ModelMovie

interface ViewPresenterMovie {

    interface MainViewMovie {
        fun showLoading()
        fun hideLoading()
        fun showData(list:List<ModelMovie>)
    }

    interface MainPresenterMovie{
        fun loadData()
    }
}