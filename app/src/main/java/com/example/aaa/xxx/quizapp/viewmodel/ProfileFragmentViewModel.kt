package com.example.aaa.xxx.quizapp.viewmodel

import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.aaa.xxx.quizapp.repository.dbRepository
import com.google.firebase.auth.FirebaseAuth

class ProfileFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val dbRepository: dbRepository = dbRepository(application)

    fun getScore(view: View, context: Context, email:String){
        dbRepository.getScore(view,context,email)
    }
}