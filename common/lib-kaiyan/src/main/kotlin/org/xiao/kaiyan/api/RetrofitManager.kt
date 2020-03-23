package org.xiao.kaiyan.api

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {
    const val UA_KEY = "User-Agent"
    const val UA_VALUE = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .build()
    }

    val kaiyanApi: KaiyanApi by lazy {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(KaiyanApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        retrofit.create(KaiyanApi::class.java)
    }
}