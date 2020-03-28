package org.xiao.kaiyan.api

import okhttp3.OkHttpClient
import org.xiao.kaiyan.gson.CardGsonConverterFactory
import retrofit2.Retrofit
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
//                .addConverterFactory(CardGsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        retrofit.create(KaiyanApi::class.java)
    }
}