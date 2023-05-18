package com.example.retrofit3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.example.retrofit3.databinding.ActivityMainBinding
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var mApiService: ApiService? = null
    lateinit var EstruturaList: ArrayList<EstruturaApi>

    private var mApiServicePost: ApiServicePost? = null
    var dadosI : DadosI? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            mApiService = ApiClient.client.create(ApiService::class.java)
            // agora chamamos o call para enfileirar a chamada e esperar a respsota
            val call = mApiService!!.fetchDados("1234")
            call!!.enqueue(object : Callback<ArrayList<EstruturaApi>?>{

                override fun onResponse(
                    call: Call<ArrayList<EstruturaApi>?>,
                    response: Response<ArrayList<EstruturaApi>?>
                ) {
                    Log.d("OK", "dados : " + response.body()!!)
                    EstruturaList = response.body()!!
                    var msg: String = " id :"
                    msg = msg + EstruturaList[2].id!! + " ra"
                    msg = msg + EstruturaList[2].ra!! + "\n"
                    msg = msg + "data: " + EstruturaList[2].data!! + "\n"
                    msg = msg + "latitude: "+EstruturaList[2].lat!! + "\n"
                    msg = msg + "longitude" + EstruturaList[2].log!! + "\n"
                    binding.resultado.text = msg

                    Log.d("Img", EstruturaList[0].img!!)
                    val imageBytes = Base64.decode(EstruturaList[0].img!!, Base64.DEFAULT)
                    var bitmap: Bitmap? = null
                    bitmap = BitmapFactory.decodeByteArray(
                        imageBytes,
                        0,
                        imageBytes.size)
                    binding.imgV.setImageBitmap(bitmap)

                }

                override fun onFailure(call: Call<ArrayList<EstruturaApi>?>, t : Throwable){
                    Log.e("Erro", "Got Error: " + t.localizedMessage)
                }
            })

            binding.btn2.setOnClickListener {
                val src: String = "data:image/jpeg:Base64"

                mApiServicePost = ApiClient.client.create(ApiServicePost::class.java)

                dadosI = DadosI("999", "-22.9015117", "-47.0717623", img = src)

                var gson = Gson().newBuilder().disableHtmlEscaping().create()
                var str = gson.toJson(dadosI)


                val call = mApiServicePost!!.sendDados(str)
                call!!.enqueue(object  : Callback<ResponseBody>{
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        Log.d("Resposta", "Resp" +response.body().toString())
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.e("Erro", "Got Error: " + t.localizedMessage)
                    }
                })

            }
        }
    }
}