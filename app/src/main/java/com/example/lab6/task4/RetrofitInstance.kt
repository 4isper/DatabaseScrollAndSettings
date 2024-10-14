package com.example.lab6.task4

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CbrApiService {
    @GET("scripts/XML_daily.asp")
    suspend fun getCurrencyRates(): String

    @GET("scripts/XML_daily.asp")
    suspend fun getCurrenciesByDate(@Query("date_req") date: String): String
}

object RetrofitInstance {
    val api: CbrApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.cbr.ru/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(CbrApiService::class.java)
    }
}
