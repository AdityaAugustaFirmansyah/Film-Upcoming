package com.example.aditya.filmupcoming.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aditya.filmupcoming.DetailMovie
import com.example.aditya.filmupcoming.R
import com.example.aditya.filmupcoming.adapter.MovieAdapter
import com.example.aditya.filmupcoming.model.ModelMovie
import com.example.aditya.filmupcoming.network.ApiClient
import com.example.aditya.filmupcoming.network.ApiInterface
import com.example.aditya.filmupcoming.presenter.MoviePresenter
import com.example.aditya.filmupcoming.utils.AppSchedullerProvider
import com.example.aditya.filmupcoming.view.ViewPresenterMovie
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.view.*
import org.jetbrains.anko.startActivity


class MovieFragment : Fragment(),ViewPresenterMovie.MainViewMovie {
    private lateinit var adapter: MovieAdapter
    private var listMovie:MutableList<ModelMovie> = mutableListOf()
    private lateinit var presenter: MoviePresenter
    override fun showLoading() {
        loading_movie.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading_movie.visibility = View.INVISIBLE
    }

    override fun showData(list: List<ModelMovie>) {
            listMovie.clear()
            listMovie.addAll(list)
            list_movie.adapter = MovieAdapter(listMovie){
                context!!.startActivity<DetailMovie>("id" to it.id,"gambar" to it.poster_path,"judul" to it.title)
            }
            list_movie.layoutManager = GridLayoutManager(activity,2)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_movie, container, false)

        adapter = MovieAdapter(listMovie){
            context!!.startActivity<DetailMovie>("id" to it.id)
        }
        rootView.list_movie.adapter = adapter
        rootView.list_movie.layoutManager = GridLayoutManager(activity,3)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val apiInterface = ApiClient.instance.create(ApiInterface::class.java)
        val scheduller = AppSchedullerProvider()
        presenter = MoviePresenter(this,apiInterface,scheduller)
        presenter.loadData()
    }

    companion object {
        fun newInstance(): MovieFragment {
            val args = Bundle()
            MovieFragment().arguments = args
            return MovieFragment()
        }
    }
}
