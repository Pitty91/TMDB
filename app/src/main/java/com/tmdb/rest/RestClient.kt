package com.tmdb.rest

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by ANDROID on 3/11/17.
 */
class RestClient {

    init {
        val gson = GsonBuilder()
                .setLenient()
                .create()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.writeTimeout(30, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)
        val retrofit = Retrofit.Builder()
                .baseUrl(URLS.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
        apiService = retrofit.create(IApiInterface::class.java)
    }

    companion object {
        private lateinit var apiService: IApiInterface

        fun getApiInterfaceService(): IApiInterface {
            RestClient()
            return apiService
        }
    }
}