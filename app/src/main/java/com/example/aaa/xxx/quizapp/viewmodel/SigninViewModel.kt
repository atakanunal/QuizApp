package com.example.aaa.xxx.quizapp.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.aaa.xxx.quizapp.acitivities.MainActivity
import com.example.aaa.xxx.quizapp.model.Login
import com.example.aaa.xxx.quizapp.model.User
import com.example.aaa.xxx.quizapp.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception
class SigninViewModel(application: Application) : AndroidViewModel(application) {
    val authRepository:AuthRepository= AuthRepository(application)

    fun loginUser(login: Login, context: Context) {
        authRepository.loginUser(login,context)
    }
}