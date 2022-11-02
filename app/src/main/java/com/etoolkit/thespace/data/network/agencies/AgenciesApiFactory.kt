package com.etoolkit.thespace.data.network.agencies

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AgenciesApiFactory {

    private const val BASE_URL = "https://ll.thespacedevs.com/2.2.0/"

    private val httpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY }).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    val agenciesApiService : AgenciesApiService = retrofit.create(AgenciesApiService::class.java)

}