package com.example.aaa.xxx.quizapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.aaa.xxx.quizapp.R
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment(R.layout.fragment_start) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnStartQuiz.setOnClickListener(){
            Navigation.findNavController(requireActivity(),R.id.navHostFragment).navigate(R.id.action_startFragment_to_homeFragment)
        }
    }
}