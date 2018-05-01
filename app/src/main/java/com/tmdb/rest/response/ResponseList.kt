package com.tmdb.rest.response

import com.tmdb.model.Movie

/**
 * Created by PRATIK BHATT on 30/4/18.
 */

class ResponseList {
    var id = 0
    var page = 0
    var results = ArrayList<Movie>()
    var total_pages = 0
    var total_results = 0


}