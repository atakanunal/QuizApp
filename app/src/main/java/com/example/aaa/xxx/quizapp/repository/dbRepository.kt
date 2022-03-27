package com.example.aaa.xxx.quizapp.repository

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.aaa.xxx.quizapp.model.User
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class dbRepository {
    private var application:Application
    private val userCollectionRef= FirebaseFirestore.getInstance().collection("users")

    constructor(application: Application){
        this.application=application
    }
    fun saveUser(context: Context,user:User)= CoroutineScope(Dispatchers.IO).launch {
        val name=user.name
        val surname=user.surname
        val password=user.password
        val gmail=user.gmail
        val userName=user.userName
        val map= mutableMapOf<String,Any>()
        map["score"]=0
        if(name.isNotEmpty() && surname.isNotEmpty() && password.isNotEmpty() && gmail.isNotEmpty() && userName.isNotEmpty()){
            try{
                val id=userCollectionRef.add(user).await()
                userCollectionRef.document(id.id).collection("score").add(map).await()
                withContext(Dispatchers.Main){
                    Toast.makeText(context,"Kayıt işlemi başarılı!",Toast.LENGTH_LONG).show()
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    fun compareScore(view:View,context: Context,usersCollectionRef: CollectionReference,score1: Int){
        usersCollectionRef.addSnapshotListener{snapshot,exception->
            if(exception!=null){
                Toast.makeText(context,exception.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if(snapshot!=null){
                    if(!snapshot.isEmpty){
                        val documents=snapshot.documents
                        for(document in documents){
                            val score=document.get("score") as Long
                            val score2=score.toInt()
                            if(score1>score2){
                                saveScore(view,context,usersCollectionRef,score1,document.id)
                            }
                        }
                    }
                }
            }
        }
    }
    fun getScore(view:View,context: Context,gmail:String){
        userCollectionRef.addSnapshotListener{snapshot,exception->
            if(exception!=null){
                Toast.makeText(context,exception.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if(snapshot!=null){
                    if(!snapshot.isEmpty){
                        val documents=snapshot.documents
                        for(document in documents){
                            val email=document.get("gmail") as String
                            if(email==gmail){
                                val name=document.get("name") as String
                                val surname=document.get("surname") as String
                                val username=document.get("userName") as String
                                view.tvUsername.text=username.toUpperCase()
                                view.tvRealName.text=name.toUpperCase()+" "+surname.toUpperCase()
                                view.tvProfileGmail.text=email.toUpperCase()
                                userCollectionRef.document(document.id).collection("score").addSnapshotListener{snapshot,exception->
                                    if(exception!=null){
                                        Toast.makeText(context,exception.localizedMessage,Toast.LENGTH_LONG).show()
                                    }else{
                                        if(snapshot!=null){
                                            if(!snapshot.isEmpty){
                                                val documents=snapshot.documents
                                                for(document in documents){
                                                    val score=document.get("score") as Long
                                                    view.tvProfileScore.text="En Yüksek Skorunuz: "+score.toInt().toString()
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    fun retrieveUser(view:View,context: Context,email:String,score:Int){
        userCollectionRef.addSnapshotListener{snapshot,exception->
            if(exception!=null){
                Toast.makeText(context,exception.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if(snapshot!=null){
                    if(!snapshot.isEmpty){
                        val documents=snapshot.documents
                        for(document in documents){
                            val gmail=document.get("gmail") as String
                            if(gmail==email){
                                val usersCollectionRef=userCollectionRef.document(document.id).collection("score")
                                compareScore(view,context,usersCollectionRef,score)

                            }
                        }
                    }
                }
            }
        }
    }

    private fun saveScore(view:View,context: Context,usersCollectionRef: CollectionReference, score: Int,documentid:String){
        val map= mutableMapOf<String,Any>()
        map["score"]=score
        usersCollectionRef.document(documentid).update("score",score)
    }



    fun updateUser(context: Context,user: User,newUserMap: Map<String,Any>)= CoroutineScope(Dispatchers.IO).launch {
        val userQuery=userCollectionRef
                .whereEqualTo("gmail",user.gmail)
                .whereEqualTo("name",user.name)
                .whereEqualTo("password",user.password)
                .whereEqualTo("score",0)
                .whereEqualTo("surname",user.surname)
                .whereEqualTo("userName",user.userName)
                .get().await()

        if(userQuery.documents.isNotEmpty()){
            try{
                for(document in userQuery){
                    userCollectionRef.document(document.id).set(
                            newUserMap,
                            SetOptions.merge()
                    ).await()
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            withContext(Dispatchers.Main){
                Toast.makeText(context,"No Users matched the query",Toast.LENGTH_SHORT).show()
            }
        }
    }
}