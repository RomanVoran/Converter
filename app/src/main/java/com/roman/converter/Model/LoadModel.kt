package com.roman.converter.Model

import android.util.Log
import com.roman.converter.AppConst
import com.roman.converter.Contracts.LoadContract
import com.roman.converter.Model.REST.CrbAPI
import com.roman.converter.Model.REST.Valute
import com.roman.converter.Model.REST.ValuteCurs
import com.roman.converter.Presenter.LoadPresenter
import com.roman.converter.ValuteList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class LoadModel(var callback:(successLoad:Boolean)->Unit) : Callback<ValuteCurs>,LoadContract.LoadModel {


    override fun load(){

        val retrofit = Retrofit
            .Builder()
            .baseUrl(AppConst.BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        val valuteService = retrofit.create(CrbAPI::class.java)

        val call = valuteService.loadValuteList()

        call.enqueue(this)
    }


    override fun onResponse(call: Call<ValuteCurs>, response: Response<ValuteCurs>) {
        if (response.isSuccessful){
            val resp = (response.body()?.valuteList)

            if (resp != null) {
                ValuteList.list = resp
                ValuteList.list.add(Valute("123","RUB","Российский рубль","1"))
                callback(true)
            }else{
                callback(false)
            }
        }
    }

    override fun onFailure(call: Call<ValuteCurs>, t: Throwable) {
        callback(false)
        t.printStackTrace()
    }

}

