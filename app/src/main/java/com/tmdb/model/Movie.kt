package com.tmdb.model

import java.io.Serializable

/**
 * Created by PRATIK BHATT on 30/4/18.
 */
class Movie() : Serializable {

    var adult = false
    var id = 0
    var backdrop_path = ""
    var original_language = ""
    var original_title = ""
    var overview = ""
    var release_date = ""
    var poster_path = ""
    var title = ""
    var popularity = 0.0
    var vote_average = ""
    var vote_count = ""
}