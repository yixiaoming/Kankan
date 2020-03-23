package org.xiao.kaiyan.api

import com.google.gson.JsonObject
import org.xiao.kaiyan.entity.AllRec
import org.xiao.kaiyan.entity.TabInfo
import retrofit2.Call
import retrofit2.http.GET

interface KaiyanApi {

    companion object {
        const val BASE_URL = "http://baobab.kaiyanapp.com/"
    }

    fun getTabInfoList(): List<TabInfo> {
        return mutableListOf<TabInfo>().apply {
            add(TabInfo(-1, "发现", "http://baobab.kaiyanapp.com/api/v7/index/tab/discovery"))
            add(TabInfo(-2, "推荐", "http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=0"))
            add(TabInfo(-3, "日报", "http://baobab.kaiyanapp.com/api/v5/index/tab/feed"))
        }
    }

    @GET("api/v5/index/tab/allRec?page=0")
    fun getAllRec(): Call<AllRec>

    @GET("api/v5/index/tab/allRec?page=0")
    fun getAllRecJson(): Call<JsonObject>

    @GET("api/v7/index/tab/discovery?vc=591&deviceModel=PCAM00")
    fun getFindMore(): Call<AllRec>

    @GET("api/v5/index/tab/feed")
    fun getFeed(): Call<AllRec>

}