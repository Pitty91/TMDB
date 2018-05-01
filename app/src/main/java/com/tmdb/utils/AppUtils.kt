package com.tmdb.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.text.TextUtils
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tmdb.model.Movie
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by ANDROID on 15/1/18.
 */

@SuppressLint("MissingPermission")
        /**
         * Check Network availability and return true or false
         *
         * @param context
         * @return
         */
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
}

fun showToast(context: Context, msg: Int) {
    Toast.makeText(context, context.getString(msg), Toast.LENGTH_SHORT).show()
}

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

/**
 * Change Date and Time format
 * as per the requirement
 *
 * @param date
 * @param currentStringFormat
 * @param desireTimeFormat
 * @return
 */
fun getTimeInGivenFormat(date: String?, currentStringFormat: String?, desireTimeFormat: String?): String {
    //Check whether all perameter is available or not
    if (date == null || TextUtils.isEmpty(date) || currentStringFormat == null || desireTimeFormat == null) {
        return ""
    }

    //Current avlilable date Format
    var format = SimpleDateFormat(currentStringFormat, Locale.getDefault())
    var newDate: Date? = null
    try {
        //Convert date to current string formate
        newDate = format.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    //Date formate in which we need to change
    format = SimpleDateFormat(desireTimeFormat, Locale.getDefault())

    //return date in desire format
    return format.format(newDate)
}

fun getMoviListLocal() : ArrayList<Movie> {
    val gson = Gson()
    val json = SharedPreferenceUtil.getString("listMovie", "")
    val type = object : TypeToken<List<Movie>>() {}.type
    var listData: ArrayList<com.tmdb.model.Movie> = gson.fromJson(json, type)
    return listData
}