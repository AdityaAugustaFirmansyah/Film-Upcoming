package com.example.aditya.filmupcoming.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.aditya.filmupcoming.BuildConfig.URL_POSTER
import com.example.aditya.filmupcoming.R
import com.example.aditya.filmupcoming.model.ModelMovie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.*
import org.jetbrains.anko.find

class MovieAdapter(private val movie:List<ModelMovie>,private val listener: (ModelMovie) -> Unit)
    :RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_item,p0,false))
    }

    override fun getItemCount(): Int {
        return movie.size
    }

    override fun onBindViewHolder(p0: MovieViewHolder, p1: Int) {
        p0.bindItem(movie[p1],listener)
    }
}

class MovieViewHolder(view:View):RecyclerView.ViewHolder(view) {
    private val textMovie:TextView = view.find(R.id.txt_title)
    private val imageMovie:ImageView = view.find(R.id.image_movie)
    fun bindItem(movie: ModelMovie,listener: (ModelMovie)->Unit){
        textMovie.text = movie.title
        Picasso.get().load(URL_POSTER+movie.poster_path).into(imageMovie)

        itemView.setOnClickListener { listener(movie) }
    }
}
