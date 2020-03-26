package org.xiao.kankan.home.recommend

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.xiao.kaiyan.api.RetrofitManager
import org.xiao.kaiyan.entity.CardList

class RecommendViewModel : ViewModel() {

    val mCardList: MutableLiveData<CardList> by lazy {
        MutableLiveData<CardList>()
    }

    fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            val content = RetrofitManager.kaiyanApi.getAllRec().execute().body()
            mCardList.postValue(content)
        }
    }
}
