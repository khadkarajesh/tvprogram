package com.nepninja.tvprogram.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nepninja.tvprogram.data.model.Tv
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


fun getClient(): OkHttpClient {
    val logger = HttpLoggingInterceptor()
    logger.setLevel(HttpLoggingInterceptor.Level.BODY)
    logger.setLevel(HttpLoggingInterceptor.Level.BASIC)
    logger.setLevel(HttpLoggingInterceptor.Level.HEADERS)

    val okHttpClient = OkHttpClient()
    okHttpClient.newBuilder().addInterceptor(logger)
    return okHttpClient
}

private val retrofit = Retrofit
    .Builder()
    .client(getClient())
    .addConverterFactory(
        TikXmlConverterFactory.create(
            TikXml.Builder().exceptionOnUnreadXml(false).build()
        )
    )
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl("https://veusat-epg.azurewebsites.net/")
    .build()

interface TvProgramService {
    @GET("epg.php")
    fun getProgrammes(@Query("api_key") apiKey: String): Deferred<Tv>
}

object Api {
    val retrofitService: TvProgramService by lazy { retrofit.create(TvProgramService::class.java) }
}
