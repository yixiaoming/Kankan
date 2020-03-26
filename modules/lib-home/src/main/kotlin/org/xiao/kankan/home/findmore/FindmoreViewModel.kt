package org.xiao.kankan.home.findmore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.xiao.kaiyan.api.RetrofitManager
import org.xiao.kaiyan.entity.CardList

class FindmoreViewModel : ViewModel() {
    val mCardList: MutableLiveData<CardList> by lazy {
        MutableLiveData<CardList>()
    }

    fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            val content = RetrofitManager.kaiyanApi.getFindMore().execute().body()
            mCardList.postValue(content)
        }
    }
}