package com.example.aditya.filmupcoming.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import com.example.aditya.filmupcoming.fragment.FavoriteMovieFragment
import com.example.aditya.filmupcoming.fragment.MovieFragment
import com.example.aditya.filmupcoming.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val content: FrameLayout? = null

    private val  mOnNavigitaionSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        item -> when(item.itemId){
        R.id.film ->{
            val fragment = MovieFragment.newInstance()
            addFragment(fragment)
            return@OnNavigationItemSelectedListener true
        }
        R.id.favorite ->{
            val fragment = FavoriteMovieFragment.newInstance()
            addFragment(fragment)
            return@OnNavigationItemSelectedListener true
        }
    }
        false
    }

    @SuppressLint("PrivateResource")
    private fun addFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.container_film,fragment,fragment.javaClass.simpleName)
            .commit()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigitaionSelectedListener)
        val fragment = MovieFragment()
        addFragment(fragment)
    }
}
