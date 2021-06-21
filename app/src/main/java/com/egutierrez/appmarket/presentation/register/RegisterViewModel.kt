package com.egutierrez.appmarket.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egutierrez.appmarket.model.Gender
import com.egutierrez.appmarket.model.GeneroDto
import com.egutierrez.appmarket.model.RegisterRequest
import com.egutierrez.appmarket.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RegisterViewModel : ViewModel() {

    private var _loader: MutableLiveData<Boolean> = MutableLiveData()
    val loader: LiveData<Boolean> = _loader

    private var _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private var _usuario: MutableLiveData<Usuario> = MutableLiveData()
    val usuario: LiveData<Usuario> = _usuario

    private var _generos: MutableLiveData<List<Gender>> = MutableLiveData()
    val generos: LiveData<List<Gender>> = _generos


    fun registerUser(request: RegisterRequest) {
        _loader.value = true
        try {
            _loader.value = true

            viewModelScope.launch {
                val response = withContext(Dispatchers.IO) {
                    Api.build().userRegister(request)
                }
                if (response.isSuccessful) {
                    if (response.body()?.success == true) {
                        _usuario.value = response.body()?.usuario
                    } else {
                        _error.value = response.body()?.message
                    }
                } else {
                    _error.value = response.message()
                }

            }
        } catch (e: Exception) {
            _error.value = e.message

        } finally {
            _loader.value = false

        }
    }

    fun getGenders() {
        viewModelScope.launch {
            _loader.postValue(true)
            try {
                val result = withContext(Dispatchers.IO) {
                    Api.build().getGenders()
                }

                if (result.isSuccessful) {
                    _generos.value = result.body()?.data
                } else {
                    _error.value = result.message()
                }

            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loader.value = false
            }
        }
    }

}