package org.xiao.kankan.home.recommend

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.xiao.kaiyan.api.RetrofitManager
import org.xiao.kaiyan.entity.CardList

class RecommendViewModel : ViewModel() {

    private var mPage: Int? = null
    private var mIsTag: Boolean? = null
    private var mAdIndex: Int? = null

    val mCardList: MutableLiveData<CardList> by lazy {
        MutableLiveData<CardList>()
    }

    fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            val content = RetrofitManager.kaiyanApi.getAllRec().execute().body()
            saveNextPageParams(content?.nextPageUrl)
            mCardList.postValue(content)
        }
    }

    private fun saveNextPageParams(nextPageUrl: String?) {
        if (nextPageUrl.isNullOrEmpty()) {
            mPage = null
            mIsTag = null
            mAdIndex = null
        } else {
            val uri = Uri.parse(nextPageUrl)
            mPage = uri.getQueryParameter("page")?.toInt()
            mIsTag = uri.getQueryParameter("isTag")?.toBoolean()
            mAdIndex = uri.getQueryParameter("adIndex")?.toInt()
        }
    }

    fun loadMoreData(): Boolean {
        if (mPage != null && mIsTag != null && mAdIndex != null) {
            GlobalScope.launch(Dispatchers.IO) {
                val content = RetrofitManager.kaiyanApi.getNextPageRec(mPage!!, mIsTag!!, mAdIndex!!).execute().body()
                saveNextPageParams(content?.nextPageUrl)
                mCardList.postValue(content)
            }
            return true
        }
        return false
    }
}
