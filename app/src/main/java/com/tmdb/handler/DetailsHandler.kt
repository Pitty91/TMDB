package com.tmdb.handler

import android.content.Context
import android.view.View
import com.tmdb.model.MovieDetails

/**
 * Created by PRATIK BHATT on 1/5/18.
 */
interface DetailsHandler {

    /**
     * Set response after fetching
     * details of the Movie
     */
    fun setResponse(response: MovieDetails)

    /**
     * return @Context
     */
    fun getContext(): Context

    /**
     * return @View
     */
    fun getParentView(): View

    /**
     * generate click event
     * for the Movie Home Page Link
     */
    fun onClickLink()
}