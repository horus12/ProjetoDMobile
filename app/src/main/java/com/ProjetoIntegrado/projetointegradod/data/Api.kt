package com.projetointegrado.projetointegradoFinal

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @POST("user/Login")
    fun login(@Body body : LoginParameters): Call<Login>
}
