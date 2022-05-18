package com.example.darren.quizapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    private var tvName: TextView? = null
    private var tvScore: TextView? = null
    private var btnFinish: Button? = null

    private var mUsername: String? = null
    private var mTotalQuestion: Int? = null
    private var mCorrectAnswer: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvName = findViewById(R.id.tv_name)
        tvScore = findViewById(R.id.tv_score)
        btnFinish = findViewById(R.id.btn_finish)

        mUsername = intent.getStringExtra(Constants.USER_NAME)
        mTotalQuestion = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        mCorrectAnswer = intent.getIntExtra(Constants.CORRECT_ANSWER, 0)

        tvName?.text = mUsername
        tvScore?.text = "Your Score is $mCorrectAnswer out of $mCorrectAnswer"

        btnFinish?.setOnClickListener { finish() }



    }
}