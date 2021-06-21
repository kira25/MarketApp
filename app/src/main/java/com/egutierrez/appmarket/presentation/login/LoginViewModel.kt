package com.egutierrez.appmarket.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egutierrez.appmarket.model.LoginRequest
import com.egutierrez.appmarket.model.Usuario
import com.egutierrez.appmarket.model.UsuarioDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//COROUTINES
//Scope
//Disptachers.IO es para llamadas asincronas como request a base de datos
//Disptachers.Main es el hilo principal(presionar boton, cambiar el color del texto,etc)
class LoginViewModel : ViewModel() {




    private var _error: MutableLiveData<String> = MutableLiveData()

    val error: LiveData<String> = _error

    private var _loader: MutableLiveData<Boolean> = MutableLiveData()

    val loader: LiveData<Boolean> = _loader

    private var _user: MutableLiveData<Usuario> = MutableLiveData()

    val user: LiveData<Usuario> = _user

    fun userLogin(username: String, password: String) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                //Mostrar el progress
                _loader.value = true
                val response = withContext(Dispatchers.IO) {
                    val request = LoginRequest(username, password, "")
                    Api.build().userLogin(request)
                }
                if (response.isSuccessful) {
                    //Respuesta correcta
                    if (response.body()?.success == true) {
                        //GOOD CREDENTIALS
                        _user.value = response.body()?.usuario
                    } else {
                        //BAD CREDENTIALS
                        _error.value = response.body()?.message
                    }

                } else {
                    println(response.message())
                    _error.value = response.message()

                }
            } catch (ex: Exception) {
                _error.value = ex.message.toString()

            } finally {
                _loader.value = false

            }


        }
    }
}