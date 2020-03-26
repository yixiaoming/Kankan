package org.xiao.kaiyan.api

import org.xiao.kaiyan.entity.CardList
import org.xiao.kaiyan.entity.TabInfo
import retrofit2.Call
import retrofit2.http.GET

interface KaiyanApi {

    companion object {
        const val BASE_URL = "http://baobab.kaiyanapp.com/"
    }

    fun getTabInfoList(): List<TabInfo> {
        return mutableListOf<TabInfo>().apply {
            add(TabInfo(-1, "发现", "http://baobab.kaiyanapp.com/api/v7/index/tab/discovery?vc=591&deviceModel=PCAM00"))
            add(TabInfo(-2, "推荐", "http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=0&vc=591&deviceModel=PCAM00"))
            add(TabInfo(-3, "日报", "http://baobab.kaiyanapp.com/api/v5/index/tab/feed?vc=591&deviceModel=PCAM00"))
        }
    }

    @GET("api/v5/index/tab/allRec?page=0&isOldUser=false&udid=4b5f9041d99f4cf0bc845658dec23211c045659a&vc=591&vn=6.2.1&size=1080X2208&deviceModel=PCAM00&first_channel=eyepetizer_web&last_channel=eyepetizer_web&system_version_code=29")
    fun getAllRec(): Call<CardList>

    @GET("api/v7/index/tab/discovery?vc=591&deviceModel=PCAM00")
    fun getFindMore(): Call<CardList>

    @GET("api/v5/index/tab/feed?vc=591&deviceModel=PCAM00")
    fun getFeed(): Call<CardList>

}