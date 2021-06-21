package com.egutierrez.appmarket.model

data class Usuario(
    val uuid: String,
    val nombres: String,
    val apellidos: String,
    val email: String,
    val celular: String,
    val genero: String,
    val nroDoc: String,
    val tipo: String
)

data class Gender(
    val genero: String,
    val descripcion: String,
) {
    override fun toString(): String {
        return "$descripcion"
    }
}