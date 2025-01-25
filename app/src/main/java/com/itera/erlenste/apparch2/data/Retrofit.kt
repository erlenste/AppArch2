package com.itera.erlenste.apparch2.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    object RetrofitHelper {
        fun getInstance(url: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getLoggingInstance(url: String): Retrofit {
            val client = OkHttpClient()
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val clientBuilder: OkHttpClient.Builder =
                client.newBuilder().addInterceptor(interceptor as HttpLoggingInterceptor)
            return Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build()
        }
    }
}