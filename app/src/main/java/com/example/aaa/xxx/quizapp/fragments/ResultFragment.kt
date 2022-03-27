package com.example.aaa.xxx.quizapp.fragments

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.aaa.xxx.quizapp.R
import com.example.aaa.xxx.quizapp.model.User
import com.example.aaa.xxx.quizapp.viewmodel.ResultFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment(R.layout.fragment_result) {
    private lateinit var viewModel: ResultFragmentViewModel
    val args:ResultFragmentArgs by navArgs()
    val application=Application()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val score=args.score
        tvScore.text="Puanın:"+score
        val gmail=FirebaseAuth.getInstance().currentUser?.email
        viewModel= ViewModelProviders.of(this).get(ResultFragmentViewModel::class.java)
        viewModel.retrieveUser(view,requireContext(),gmail!!,score)
        btnGotoProfile.setOnClickListener(){
            val action=ResultFragmentDirections.actionResultFragmentToProfileFragment(score)
            Navigation.findNavController(requireActivity(),R.id.navHostFragment).navigate(action)
        }
    }
}