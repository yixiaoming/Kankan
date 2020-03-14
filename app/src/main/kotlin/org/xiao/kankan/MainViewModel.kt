package org.xiao.kankan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val user: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getUser(): LiveData<String> {
        return user
    }

    fun loadUser() {
        user.value = "nihao"
    }
}