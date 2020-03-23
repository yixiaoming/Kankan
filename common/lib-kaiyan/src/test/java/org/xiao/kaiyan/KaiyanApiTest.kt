package org.xiao.kaiyan

import org.junit.Test
import org.xiao.kaiyan.api.RetrofitManager.kaiyanApi
import java.io.IOException

class KaiyanApiTest {
    @get:Throws(IOException::class)
    @get:Test
    val allRec: Unit
        get() {
            val allRec = kaiyanApi.getAllRec().execute().body()
            for ((_, data) in allRec!!.itemList) {
                println(data.title)
            }
        }

    @get:Throws(IOException::class)
    val findMore: Unit
        get() {
            val allRec = kaiyanApi.getFindMore().execute().body()
        }
}
