package com.example.aaa.xxx.quizapp.acitivities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.aaa.xxx.quizapp.R
import com.example.aaa.xxx.quizapp.model.User
import com.example.aaa.xxx.quizapp.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(){
    private lateinit var viewModel: RegisterViewModel
    private var firebaseAuth: FirebaseAuth= FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        viewModel= ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        signIn()
        getInfoForRegister(applicationContext)
        checkLoggedInState()
    }
    private fun signIn(){
        tvLogin.setOnClickListener(){
            val intent=Intent(this@RegisterActivity,SignInActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getInfoForRegister(context: Context){
        btnLogin.setOnClickListener(){
            val name=etName.text.toString()
            val surName=etSurname.text.toString()
            val gmail=etGmail.text.toString()
            val userName=etUser.text.toString()
            val password=etPassword.text.toString()
            val user= User(name,surName,gmail,password,userName,0)
            viewModel.registerUser(user,context)
        }
    }
    private fun checkLoggedInState(){
        if(firebaseAuth.currentUser!=null){
            val intent= Intent(this@RegisterActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}