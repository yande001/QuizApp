package com.example.darren.quizapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity : AppCompatActivity() {
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvDescription: TextView? = null
    private var ivImage: ImageView? = null
    private  var tvOption1: TextView? = null
    private  var tvOption2: TextView? = null
    private  var tvOption3: TextView? = null
    private  var tvOption4: TextView? = null
    private  var btnSubmit: Button? = null

    private var currentPosition = 1
    private var questionsList: ArrayList<Question>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)
        tvDescription = findViewById(R.id.tvDescription)
        ivImage = findViewById(R.id.ivImage)
        tvOption1 = findViewById(R.id.tvOption1)
        tvOption2 = findViewById(R.id.tvOption2)
        tvOption3 = findViewById(R.id.tvOption3)
        tvOption4 = findViewById(R.id.tvOption4)
        btnSubmit = findViewById(R.id.btnSubmit)

        questionsList = Constants.getQuestions()

        setUI(currentPosition)

        btnSubmit?.setOnClickListener {
            setUI(++currentPosition)
        }




    }

    private fun setUI(position: Int){
        progressBar?.progress = currentPosition
        tvProgress?.text = "$currentPosition/${questionsList?.size}"
        var question =questionsList?.get(position-1)
        question?.image?.let { ivImage?.setImageResource(it) }
        tvDescription?.text = question?.description
        tvOption1?.text = question?.optionOne
        tvOption2?.text = question?.optionTwo
        tvOption3?.text = question?.optionThree
        tvOption4?.text = question?.optionFour
    }


}