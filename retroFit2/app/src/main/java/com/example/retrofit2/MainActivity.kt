package com.example.retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofit2.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var mApiService: APIService? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {

            mApiService = RestClient.client.create(APIService::class.java)
            val call = mApiService!!.fetchQuestions("android")
            call.enqueue(object: Callback<QuestionList>{
                override fun onResponse(call: Call<QuestionList>,response: Response<QuestionList>) {
                    Log.d("RETTO", "TOTAL " + response.body()!!.items!!.size)

                }
                override fun onFailure(call: Call<QuestionList>, t: Throwable) {
                    Log.e("TAG", "Got Error : " + t.localizedMessage)
                }
                })
            }

        }
    }


object RestClient {

    private val BASE_URL = "https://api.stackexchange.com"
            private var mRetrofit: Retrofit? = null

    val client: Retrofit
        get(){
            if(mRetrofit == null){
                mRetrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return this.mRetrofit!!
        }


}
//mapea os objetos json (chaves) para um objeto
class Question{
    val title: String? = null
    val lin: String? = null
}

class QuestionList {
    val items: List<Question>? = null
    val has_nome: Boolean? = null
    val quota_MAX: Number? = null
    val quota_remaining: Number? = null
}
//interface para acessar end point (retrofit)
interface APIService {
    @GET("/2.2/quaestions?order=desc&sort=creation&site=stackoverflow")
    fun fetchQuestions(@Query("tagged") tags: String): Call<QuestionList>
}