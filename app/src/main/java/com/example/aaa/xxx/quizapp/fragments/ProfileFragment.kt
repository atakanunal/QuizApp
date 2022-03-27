package com.example.aaa.xxx.quizapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.aaa.xxx.quizapp.R
import com.example.aaa.xxx.quizapp.acitivities.SignInActivity
import com.example.aaa.xxx.quizapp.viewmodel.ProfileFragmentViewModel
import com.example.aaa.xxx.quizapp.viewmodel.ResultFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    val firebaseAuth:FirebaseAuth= FirebaseAuth.getInstance()
    private lateinit var viewModel: ProfileFragmentViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCikisYap.setOnClickListener(){
            firebaseAuth.signOut()
            val intent=Intent(activity,SignInActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
        }
        val gmail=FirebaseAuth.getInstance().currentUser?.email
        viewModel= ViewModelProviders.of(this).get(ProfileFragmentViewModel::class.java)
        viewModel.getScore(view,requireContext(), gmail!!)
    }
}