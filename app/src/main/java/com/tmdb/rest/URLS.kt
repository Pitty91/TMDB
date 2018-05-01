package com.tmdb.rest

/**
 * Created by ANDROID on 3/11/17.
 */

class URLS {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        const val LIST_MOVIE = "3/genre/{id}/movies?api_key=94d3c9e0b2feed813b1348937d7bffe0"
        const val DETAILS_MOVIE = "3/movie/{id}?api_key=94d3c9e0b2feed813b1348937d7bffe0"
    }
}