package com.example.aaa.xxx.quizapp.repository

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.aaa.xxx.quizapp.acitivities.MainActivity
import com.example.aaa.xxx.quizapp.acitivities.SignInActivity
import com.example.aaa.xxx.quizapp.model.Login
import com.example.aaa.xxx.quizapp.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class AuthRepository{
    private var application:Application
    private var firebaseAuth: FirebaseAuth
    private var dbRepository:dbRepository


    constructor(application: Application){
        this.application=application
        this.firebaseAuth= FirebaseAuth.getInstance()
        this.dbRepository=dbRepository(application)
    }

    fun loginUser(login: Login, context: Context) {
        val gmail = login.gmail
        val password = login.password
        if (gmail.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    firebaseAuth.signInWithEmailAndPassword(gmail, password).await()
                    withContext(Dispatchers.Main) {
                        var intent = Intent(context, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun registerUser(user: User,context: Context){
        val name=user.name
        val surName=user.surname
        val gmail=user.gmail
        val password=user.password
        val userName=user.userName
        if(name.isNotEmpty() && surName.isNotEmpty() && gmail.isNotEmpty() && password.isNotEmpty() && userName.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    firebaseAuth.createUserWithEmailAndPassword(gmail,password).await()
                    withContext(Dispatchers.Main){
                        dbRepository.saveUser(context,user)
                        var intent = Intent(context, SignInActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}