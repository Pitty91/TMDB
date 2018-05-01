package com.tmdb.handler

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.tmdb.model.Movie

/**
 * Created by PRATIK BHATT on 30/4/18.
 */

interface ListHandler {

    /**
     * Set response after fetching data from
     * the movieList API
     */
    fun setResponse(response: ArrayList<Movie>)

    /**
     * Click on the movie Item
     * and move to the Movie Details Screen
     */
    fun onClickItem(movie: Movie, ivBanner: ImageView, tvTitle: TextView, tvRating: TextView)

    /**
     * return @Context
     */
    fun getContext(): Context

    /**
     * return @View
     */
    fun getParentView(): View
}