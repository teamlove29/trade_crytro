package com.alw.trade

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.alw.trade.Interfact.INetworkAPI
import com.alw.trade.Modal.DataToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_sell.setOnClickListener {
            btn_buy.setBackgroundColor(resources.getColor(R.color.gray))
            btn_sell.setBackgroundColor(resources.getColor(R.color.red))

            btn_submit.apply {
                text = "SELL"
                setBackgroundColor(resources.getColor(R.color.red))
            }
        }

        btn_buy.setOnClickListener {
            btn_buy.setBackgroundColor(resources.getColor(R.color.green))
            btn_sell.setBackgroundColor(resources.getColor(R.color.gray))
            btn_submit.apply {
                text = "BUY"
                setBackgroundColor(resources.getColor(R.color.green))
            }
        }


        ic_add.setOnClickListener {
            var count = edit_text_amount.text.toString()
            if(count != ""){
                var countPar = count.toDouble()
                countPar += 1.00
                edit_text_amount.setText("${"%.2f".format(countPar)}")
                edit_text_amount.isCursorVisible = false
            }else{
                edit_text_amount.setText("1.00")
            }
        }



        ic_reduce.setOnClickListener {
            var count = edit_text_amount.text.toString()
            if(count != ""){
                var countPar = count.toDouble()
                if(countPar > 0.00){
                    countPar -= 1.00
                    edit_text_amount.setText("${"%.2f".format(countPar)}")
                    edit_text_amount.isCursorVisible = false
                }

            }
        }



//        edit_text_amount.setOnClickListener {
//
//        }

        edit_text_amount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //ก่อนเปลี่ยนคือ ?
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isEmpty()) {
                    edit_text_amount.hint = "Total(USD)"
                } else {
                    edit_text_amount.isCursorVisible = true
                    edit_text_amount.hint = ""
                    if (edit_text_amount.text.toString() == "."
                        || edit_text_amount.text.toString() == "00"
                    ) {
                        edit_text_amount.setText("0.")
                        edit_text_amount.setSelection(edit_text_amount.text.toString().length)
                    }
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        getDataFromAPI()




    }




    private fun getDataFromAPI(){
        val BASE_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/"
//        val dataInfo = PostStation(uid, station)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val request: Request = chain.request().newBuilder().addHeader("X-CMC_PRO_API_KEY", "c38898ee-97a7-4da9-a9cd-d3be44cfc897").build()
            chain.proceed(request)
        }

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(INetworkAPI::class.java)

        val requireCall = api.getDataToken()

        requireCall.enqueue(object : Callback<List<DataToken>> {
            override fun onResponse(
                call: Call<List<DataToken>>,
                response: Response<List<DataToken>>
            ) {
                if (response.code() != 200) {
                    return
                }
                var dataList = response.body()
                println("dasasd ${dataList!![1].slug}")
            }

            override fun onFailure(call: Call<List<DataToken>>, t: Throwable) {
                println("errorPost  ${t.message} ")
            }
        })
    }


}