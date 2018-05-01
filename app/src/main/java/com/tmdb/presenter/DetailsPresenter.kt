package com.tmdb.presenter

import android.util.Log
import com.tmdb.R
import com.tmdb.handler.DetailsHandler
import com.tmdb.model.MovieDetails
import com.tmdb.rest.RestClient
import com.tmdb.utils.Constant
import com.tmdb.utils.DialogUtils
import com.tmdb.utils.isNetworkAvailable
import com.tmdb.utils.showToast
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

@Suppress("IMPLICIT_CAST_TO_ANY")
/**
 * Created by PRATIK BHATT on 1/5/18.
 */
class DetailsPresenter(var handler: DetailsHandler) {

    fun getMovieDetails(id: String) = when {
        isNetworkAvailable(handler.getContext()) -> {
            DialogUtils.showProgressDialog(handler.getContext())
            val responseUserObservable = RestClient.getApiInterfaceService().getMovieDetails(id)
            responseUserObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(object : Subscriber<MovieDetails>() {
                        override fun onNext(t: MovieDetails?) {
                            handler.setResponse(t!!)
                        }

                        override fun onError(e: Throwable?) {
                            DialogUtils.stopProgressDialog()
                        }

                        override fun onCompleted() {
                            DialogUtils.stopProgressDialog()
                            Log.e(Constant.TAG, "onCompleted: ")
                        }
                    })
        }
        else -> {
            showToast(handler.getContext(), R.string.no_internet)
        }
    }!!

}