package com.juan.formularioapilogin

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.consumokotlinsimple.interfaces.ApiService
import com.example.consumokotlinsimple.models.postUser.EnviarUsuario
import com.example.consumokotlinsimple.models.postUser.RespuestaPostUsuario
import com.juan.formularioapilogin.databinding.ActivityMainBinding
import com.juan.formularioapilogin.models.postUser.LoguinUser
import com.juan.formularioapilogin.models.postUser.ResultLogIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var identificacion:String =""
    private var password:String =""
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onclicks()

    }

private  fun onclicks(){
    binding.btnOk.setOnClickListener(){
        enviarPostUsuario ()
        //Toast.makeText( this, "Respue", Toast.LENGTH_SHORT).show()
    }
}

    private fun enviarPostUsuario() {

        val retro = Retrofit.Builder()
            .baseUrl("http://192.168.192.57:8057/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: ApiService = retro.create(ApiService::class.java)
        service.postUsuario(
            EnviarUsuario(
                binding.edtEmail.text.toString(), binding.edtPassword.text.toString()
            )
        ).enqueue(object : Callback<ResultLogIn> {
            override fun onResponse(
                call: Call<ResultLogIn>,
                response: Response<ResultLogIn>
            ) {
                Log.e("onResponse: ",response.message().toString() )
                Log.e("onResponse: ",response.code().toString() )
                Log.e("onResponse: ", response.body().toString())
               binding.edtEmail.setText(response.body()?.token)
               binding.edtPassword.setText(response.body()?.userForToken?.identificacion)
            }

            override fun onFailure(call: Call<ResultLogIn>, t: Throwable) {
                Log.e("onFailure: ", t.toString())
            }
        })

    }

}