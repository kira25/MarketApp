package com.egutierrez.appmarket.presentation.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.egutierrez.appmarket.R
import com.egutierrez.appmarket.databinding.FragmentRegisterBinding
import com.egutierrez.appmarket.model.Gender
import com.egutierrez.appmarket.model.RegisterRequest
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.header.view.*


class RegisterFragment : Fragment(R.layout.fragment_register) {
    //debes inicializarlo
    private lateinit var binding: FragmentRegisterBinding

    //Suscribir el viewmodel
    private val viewModel: RegisterViewModel by viewModels()

    private var generosGlobal: List<Gender> = listOf<Gender>()
    private var generoInput = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        init()
        events()
        setupObservables()


    }

    private fun events() = with(binding) {
        btnRegister.setOnClickListener {
            val names = edtNames.editText?.text.toString()
            val lastname = edtLastname.editText?.text.toString()
            val email = edtEmail.editText?.text.toString()
            val cellphone = edtCellphone.editText?.text.toString()
            val document = edtDocument.editText?.text.toString()
            val password = edtPassword.editText?.text.toString()

            if (validarCampos())
                viewModel.registerUser(
                    RegisterRequest(
                        names,
                        lastname,
                        email,
                        password,
                        cellphone,
                        generoInput,
                        document,
                        ""
                    )
                )
        }

        spGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int,
                id: Long
            ) {
                generoInput = generosGlobal[position].genero
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


    }

    private fun validarCampos(): Boolean {
        if (binding.edtNames.editText?.text.toString().isEmpty()) {
            edtNames.error = "Debe ingresar sus nombres"
            return false
        }
        if (binding.edtLastname.editText?.text.toString().isEmpty()) {
            edtLastname.error = "Debe ingresar sus apellidos"
            return false
        }



        return true
    }

    private fun setupObservables() = with(binding) {
        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(), "$error", Toast.LENGTH_LONG).show()
        })

        viewModel.loader.observe(viewLifecycleOwner, Observer { condicion ->
            if (condicion) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        })



        viewModel.generos.observe(viewLifecycleOwner, Observer { generos ->
            //Pintar en el combo

            spGender.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, generos)
            generosGlobal = generos
        })

        viewModel.usuario.observe(viewLifecycleOwner, Observer { usuario ->
            if (usuario != null) {
                Toast.makeText(requireContext(), "Bienvenido ${usuario.nombres}", Toast.LENGTH_LONG)
                    .show()
                requireActivity().onBackPressed()
            }
        })

    }

    private fun init() {
        //para evitar poner binding en cada linea
        includeRegister.tvTitle.text = "Crear Cuenta"
        includeRegister.img_back.setOnClickListener {
            requireActivity().onBackPressed()

        }

        viewModel.getGenders()
    }


}