package com.example.aaa.xxx.quizapp.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.aaa.xxx.quizapp.acitivities.MainActivity
import com.example.aaa.xxx.quizapp.acitivities.SignInActivity
import com.example.aaa.xxx.quizapp.model.Login
import com.example.aaa.xxx.quizapp.model.User
import com.example.aaa.xxx.quizapp.repository.AuthRepository
import com.example.aaa.xxx.quizapp.repository.dbRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.rpc.context.AttributeContext
import kotlinx.android.synthetic.main.activity_register.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class RegisterViewModel(application: Application) : AndroidViewModel(application){
    val authRepository:AuthRepository= AuthRepository(application)

    fun registerUser(user:User,context: Context){
        authRepository.registerUser(user,context)
    }





}