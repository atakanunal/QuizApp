package com.example.aaa.xxx.quizapp.util

import com.example.aaa.xxx.quizapp.R
import com.example.aaa.xxx.quizapp.model.Question

object Constants {
    fun getQuestions():ArrayList<Question>{
        val questionsList=ArrayList<Question>()

        val question1=Question(
        0,
        "Bu ülkenin bayrağı aşağıdakilerden hangisidir?",
        R.drawable.turkiye, "Türkiye",
        "Almanya",
        "Norveç",
         "Güney Africa",
          1
        )
        questionsList.add(question1)
        val question2=Question(
                1,
                "Bu ülkenin bayrağı aşağıdakilerden hangisidir?",
                R.drawable.uruguay,
                "Senegal",
                "Uruguay",
                "Portekiz",
                "Suudi Arabistan",
                2
        )
        questionsList.add(question2)
        val question3=Question(
                2,
                "Bu ülkenin bayrağı aşağıdakilerden hangisidir?",
                R.drawable.tunus,
                "ABD",
                "Tunus",
                "Türkiye",
                "Japonya",
                2
        )
        questionsList.add(question3)
        val question4=Question(
                3,
                "Bu ülkenin bayrağı aşağıdakilerden hangisidir?",
                R.drawable.suudiarabistan,
                "Almanya",
                "Güney Kore",
                "Suudi Arabistan",
                "Uruguay",
                3
        )
        questionsList.add(question4)
        val question5=Question(
                4,
                "Bu ülkenin bayrağı aşağıdakilerden hangisidir?",
                R.drawable.senegal,
                "Senegal",
                "Norveç",
                "Portekiz",
                "Cezayir",
                1
        )
        questionsList.add(question5)
        val question6=Question(
                5,
                "Bu ülkenin bayrağı aşağıdakilerden hangisidir?",
                R.drawable.fransa,
                "Fransa",
                "İngiltere",
                "Macaristan",
                "Avusturya",
                1
        )
        questionsList.add(question6)
        val question7=Question(
                6,
                "Bu ülkenin bayrağı aşağıdakilerden hangisidir?",
                R.drawable.ukrayna,
                "Rusya",
                "Moğolistan",
                "Romanya",
                "Ukrayna",
                4
        )
        questionsList.add(question7)
        val question8=Question(
                7,
                "Bu ülkenin bayrağı aşağıdakilerden hangisidir?",
                R.drawable.guneyafrika,
                "İtalya",
                "Kuzey Kore",
                "Güney Afrika",
                "Arnavutluk",
                3
        )
        questionsList.add(question8)
        val question9=Question(
                8,
                "Bu ülkenin bayrağı aşağıdakilerden hangisidir?",
                R.drawable.iran,
                "Ermenistan",
                "İran",
                "Gürcistan",
                "Irak",
                2
        )
        questionsList.add(question9)
        val question10=Question(
                9,
                "Bu ülkenin bayrağı aşağıdakilerden hangisidir?",
                R.drawable.letonya,
                "Letonya",
                "Sırbistan",
                "İzlanda",
                "Kazakistan",
                1
        )
        questionsList.add(question10)
        return questionsList
    }
}