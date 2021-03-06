package com.example.aditya.filmupcoming.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aditya.filmupcoming.R


class FavoriteMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    companion object {
        fun newInstance() : FavoriteMovieFragment {

            val args = Bundle()
            FavoriteMovieFragment().arguments = args
            return FavoriteMovieFragment()
        }
    }


}
