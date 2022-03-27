package com.example.aaa.xxx.quizapp.fragments

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.aaa.xxx.quizapp.R
import com.example.aaa.xxx.quizapp.model.Question
import com.example.aaa.xxx.quizapp.util.Constants
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home),View.OnClickListener{
    private var mQuestionsList:ArrayList<Question>?=null
    private var mCurrentPosition: Int = 1
    private var score: Int = 0
    private var mSelectedOptionPosition: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mQuestionsList = Constants.getQuestions()
        setQuestion()


        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)
    }

    private fun setQuestion() {
        val question= mQuestionsList!![mCurrentPosition - 1]
        defaultOptionsView()
        if(mCurrentPosition==mQuestionsList!!.size){
            btnSubmit?.text="Finish"
        }else{
            btnSubmit?.text="Tıkla"
        }
        progressBar?.progress=mCurrentPosition
        tvProgress?.text="$mCurrentPosition"+"/"+progressBar?.max

        tvQuestion?.text=question.question
        ivFlag?.setImageResource(question.image)
        tv_option_one?.text=question.optionOne
        tv_option_two?.text=question.optionTwo
        tv_option_three?.text=question.optionThree
        tv_option_four?.text=question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        tv_option_one?.let {
            options.add(0, it)
        }
        tv_option_two?.let {
            options.add(1, it)
        }
        tv_option_three?.let {
            options.add(2, it)
        }
        tv_option_four?.let {
            options.add(3,it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tv_option_one->{
                tv_option_one?.let {
                    selectedOptionView(it,1)
                }
            }
            R.id.tv_option_two->{
                tv_option_two?.let {
                    selectedOptionView(it,2)
                }
            }
            R.id.tv_option_three->{
                tv_option_three?.let {
                    selectedOptionView(it, 3)
                }

            }
            R.id.tv_option_four->{
                tv_option_four?.let {
                    selectedOptionView(it,4)
                }
            }
            R.id.btnSubmit->{
                if(mSelectedOptionPosition==0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionsList!!.size->{
                            setQuestion()
                        }else->{
                            val action=HomeFragmentDirections.actionHomeFragmentToResultFragment(score)
                            Navigation.findNavController(requireActivity(),R.id.navHostFragment).navigate(action)
                        }
                    }
                }else{
                    val question=mQuestionsList?.get(mCurrentPosition-1)
                    if(question!!.correctOption != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option_border_bg)
                    }else{
                        score=score+10
                    }

                    answerView(question.correctOption,R.drawable.correct_option_border_bg)

                    if(mCurrentPosition==mQuestionsList!!.size){
                        btnSubmit?.text="Bitti"
                    }else{
                        btnSubmit?.text="Sıradaki Soru"
                    }
                    mSelectedOptionPosition=0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when(answer){
            1->{
                tv_option_one?.background=ContextCompat.getDrawable(requireContext(),drawableView)
            }
            2->{
                tv_option_two?.background=ContextCompat.getDrawable(requireContext(),drawableView)
            }
            3->{
                tv_option_three?.background=ContextCompat.getDrawable(requireContext(),drawableView)
            }
            4->{
                tv_option_four?.background=ContextCompat.getDrawable(requireContext(),drawableView)
            }
        }
    }

    private fun selectedOptionView(tv: TextView, i: Int) {
        defaultOptionsView()

        mSelectedOptionPosition=i

        tv.setTextColor(
                Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(
                requireContext(),
                R.drawable.selected_option_border_bg
        )
    }
}

