package com.example.aaa.xxx.quizapp.acitivities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.aaa.xxx.quizapp.R
import com.example.aaa.xxx.quizapp.model.Login
import com.example.aaa.xxx.quizapp.model.User
import com.example.aaa.xxx.quizapp.viewmodel.SigninViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_signin.*

class SignInActivity : AppCompatActivity() {
    private lateinit var viewModel:SigninViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        viewModel=ViewModelProviders.of(this@SignInActivity).get(SigninViewModel::class.java)
        loginForUser(applicationContext)
    }
    private fun loginForUser(context:Context){
        btnLogin2.setOnClickListener(){
            val name=etGmailSignin.text.toString()
            val password=etPasswordSignin.text.toString()
            val login= Login(name,password)
            viewModel.loginUser(login,context)
        }
        tvSignin.setOnClickListener(){
            val intent=Intent(this@SignInActivity,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}