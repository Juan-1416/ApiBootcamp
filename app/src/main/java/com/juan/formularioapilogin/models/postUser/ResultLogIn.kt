package com.juan.formularioapilogin.models.postUser

data class ResultLogIn(
    val userForToken : LoguinUser,
    val token : String,
)
