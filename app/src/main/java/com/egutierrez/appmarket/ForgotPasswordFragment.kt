package com.egutierrez.appmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_forgot_password.*
import kotlinx.android.synthetic.main.header.view.*


class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {
    // TODO: Rename and change types of parameters

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        includeForgotPassword.tvTitle.text = "Recuperar contraseña"
        includeForgotPassword.img_back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}