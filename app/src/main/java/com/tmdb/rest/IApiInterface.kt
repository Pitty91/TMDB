package com.tmdb.rest

import com.tmdb.model.MovieDetails
import com.tmdb.rest.response.ResponseList
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by PRATIK BHATT on 30/4/18.
 */

interface IApiInterface {

    @GET(URLS.LIST_MOVIE)
    fun getMovieList(@Path("id") id: String): Observable<ResponseList>

    @GET(URLS.DETAILS_MOVIE)
    fun getMovieDetails(@Path("id") id: String): Observable<MovieDetails>
}