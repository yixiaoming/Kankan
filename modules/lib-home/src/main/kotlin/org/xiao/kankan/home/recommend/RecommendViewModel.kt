package org.xiao.kankan.home.recommend

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.xiao.kaiyan.api.RetrofitManager
import org.xiao.kaiyan.entity.AllRec

class RecommendViewModel : ViewModel() {

    val mAllRec: MutableLiveData<AllRec> by lazy {
        MutableLiveData<AllRec>()
    }

    fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            val content = RetrofitManager.kaiyanApi.getAllRec().execute().body()
            mAllRec.postValue(content)
        }
    }
}
