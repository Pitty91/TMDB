package com.tmdb.presenter

import android.util.Log
import com.google.gson.Gson
import com.tmdb.R
import com.tmdb.handler.ListHandler
import com.tmdb.rest.RestClient
import com.tmdb.rest.response.ResponseList
import com.tmdb.utils.*
import com.tmdb.utils.Constant.Companion.TAG
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

@Suppress("IMPLICIT_CAST_TO_ANY")
/**
 * Created by PRATIK BHATT on 30/4/18.
 */
class ListPresenter {
    fun getMovieList(handler: ListHandler) = when {
        isNetworkAvailable(handler.getContext()) -> {
            DialogUtils.showProgressDialog(handler.getContext())

            val responseUserObservable = RestClient.getApiInterfaceService().getMovieList("28")
            responseUserObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(object : Subscriber<ResponseList>() {
                        override fun onNext(t: ResponseList?) {
                            val gson = Gson()
                            val json = gson.toJson(t?.results)
                            SharedPreferenceUtil.putValue("listMovie", json)
                            SharedPreferenceUtil.save()
                            handler.setResponse(t?.results!!)
                        }

                        override fun onError(e: Throwable?) {
                            DialogUtils.stopProgressDialog()
                            handler.setResponse(getMoviListLocal())
                        }

                        override fun onCompleted() {
                            DialogUtils.stopProgressDialog()
                            Log.e(TAG, "onCompleted: ")
                        }
                    })
        }
        else -> {
            showToast(handler.getContext(), R.string.no_internet)
            handler.setResponse(getMoviListLocal())
        }
    }
}