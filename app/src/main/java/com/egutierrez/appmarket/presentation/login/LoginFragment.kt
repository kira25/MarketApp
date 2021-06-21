package com.egutierrez.appmarket.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.egutierrez.appmarket.R
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(R.layout.fragment_login) {


    private val viewModel: LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventos(view)
        setupObservables()


    }

    private fun setupObservables() {
        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(), "$error ", Toast.LENGTH_LONG).show()
        })

        viewModel.loader.observe(viewLifecycleOwner, Observer { condicion ->
            if (condicion == true) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        })

        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            Toast.makeText(requireContext(), "${user.nombres} ${user.apellidos}", Toast.LENGTH_LONG)
                .show()

        })
    }

    private fun eventos(view:View) {
        btn_login.setOnClickListener {
            //MVVM
            //El textinputlayout recibe el ID
            val username = edtUsername.editText?.text.toString()
            val password = edtPassword.editText?.text.toString()
            viewModel.userLogin(username, password)

        }


        tvCreateAccount.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)
        }
        tvForgotPassword.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
    }


}
