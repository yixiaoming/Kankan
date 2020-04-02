package org.xiao.kankan.home.feed

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.xiao.kaiyan.api.RetrofitManager
import org.xiao.kaiyan.entity.CardList

class FeedViewModel : ViewModel() {

    private var mNextPageDate: Long? = null
    private var mNextPageNum: Int? = null

    val mCardList: MutableLiveData<CardList> by lazy {
        MutableLiveData<CardList>()
    }

    fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            val content = RetrofitManager.kaiyanApi.getFeed().execute().body()
            saveNextPageParams(content?.nextPageUrl)
            mCardList.postValue(content)
        }
    }

    fun loadMoreData(): Boolean {
        if (mNextPageDate != null && mNextPageNum != null) {
            GlobalScope.launch(Dispatchers.IO) {
                val content = RetrofitManager.kaiyanApi.getNextPageFeed(mNextPageDate!!, mNextPageNum!!).execute().body()
                saveNextPageParams(content?.nextPageUrl)
                mCardList.postValue(content)
            }
            return true
        }
        return false
    }

    private fun saveNextPageParams(url: String?) {
        if (url.isNullOrEmpty()) {
            mNextPageDate = null
            mNextPageNum = null
        } else {
            val uri = Uri.parse(url)
            mNextPageDate = uri.getQueryParameter("date")?.toLong()
            mNextPageNum = uri.getQueryParameter("num")?.toInt()
        }
    }
}