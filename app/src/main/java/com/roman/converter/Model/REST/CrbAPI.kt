package com.roman.converter.Model.REST

import retrofit2.Call
import retrofit2.http.GET

public interface CrbAPI {

    @GET("scripts/XML_daily.asp")
    fun loadValuteList(): Call<ValuteCurs>

}