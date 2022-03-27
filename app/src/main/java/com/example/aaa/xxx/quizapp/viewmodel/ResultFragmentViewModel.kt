package com.example.aaa.xxx.quizapp.viewmodel

import android.app.Application
import android.content.Context
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import com.example.aaa.xxx.quizapp.repository.dbRepository
import com.google.firebase.auth.FirebaseAuth
import com.example.aaa.xxx.quizapp.model.User

class ResultFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val dbRepository: dbRepository = dbRepository(application)


    fun retrieveUser(view:View,context: Context,email:String,score:Int){
        dbRepository.retrieveUser(view,context,email,score)
    }
}