package com.example.consumokotlinsimple.interfaces

import com.example.consumokotlinsimple.models.ResponseResult
import com.example.consumokotlinsimple.models.postUser.RespuestaPostUsuario
import com.example.consumokotlinsimple.models.postUser.EnviarUsuario
import com.juan.formularioapilogin.models.postUser.LoguinUser
import com.juan.formularioapilogin.models.postUser.ResultLogIn
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("login")
    fun getUsers(): Call<ResponseResult>

    @POST("login")
    fun postUsuario(@Body enviarUsuario: EnviarUsuario):Call<ResultLogIn>
}