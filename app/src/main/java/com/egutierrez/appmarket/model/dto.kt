package com.egutierrez.appmarket.model

import com.google.gson.annotations.SerializedName

data class UsuarioDto(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String = "",
    @SerializedName("data")
    val usuario: Usuario?
) {
}

data class RegisterDto(
    val success: Boolean, val message: String, val token: String,
    val data: Usuario
) {

}

data class GeneroDto(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Gender>

)