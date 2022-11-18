package ran.am.androidmanifestercodeskotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    val myApi: Api

    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(Api.Companion.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        myApi = retrofit.create(Api::class.java)
    }

    companion object {
        fun getInstance() {

        }

        @get:Synchronized
        var instance: RetrofitClient? = null
            get() {
                if (field == null) {
                    field = RetrofitClient()
                }
                return field
            }
            private set
    }
}