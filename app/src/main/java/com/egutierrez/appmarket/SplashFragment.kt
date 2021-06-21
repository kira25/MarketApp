package com.egutierrez.appmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_splash.*

//View Model  : Se encarga del ciclo de vida del fragmento o actvidad, sobrevive al cambio de configuracion
//Live Data: proporciona la ultima informacion
//MutableLiveData: puedes cambiarle el valor /obtener el valor

//


class SplashFragment : Fragment() {

    //Inflar la vista
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNavegar.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment);
        }
    }
}