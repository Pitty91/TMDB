package com.tmdb.utils

import android.app.Application
import android.content.Context

/**
 * Created by ANDROID on 30/4/18.
 */

class TMDB : Application() {
    companion object {
        private lateinit var mInstance: TMDB
        private lateinit var context: Context

        fun getInstance(): TMDB {
            return mInstance;
        }

        fun getContext1(): Context {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        TMDB.context = this
        mInstance = this

        SharedPreferenceUtil.init(this)
        SharedPreferenceUtil.save()

    }
}