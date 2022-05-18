package com.example.darren.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvDescription: TextView? = null
    private var ivImage: ImageView? = null
    private  var tvOption1: TextView? = null
    private  var tvOption2: TextView? = null
    private  var tvOption3: TextView? = null
    private  var tvOption4: TextView? = null
    private  var btnSubmit: Button? = null

    private var mCurrentPosition = 1
    private var mQuestionsList: ArrayList<Question>? = null
    //0,1,2,3
    private var mSelectedOptionPosition: Int? = null
    // status 0 -> submit, 1 -> next, 2 -> finish
    private var mBtnStatus = 0
    private var mWrongCount = 0

    private var mUsername: String? = null

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

        tvOption1?.setOnClickListener(this)
        tvOption2?.setOnClickListener(this)
        tvOption3?.setOnClickListener(this)
        tvOption4?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mUsername = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestions()

        setUI(mCurrentPosition)
    }

    private fun setUI(position: Int){
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${mQuestionsList?.size}"
    var question = mQuestionsList!![position-1]
        question?.image?.let { ivImage?.setImageResource(it) }
        tvDescription?.text = question?.description
        tvOption1?.text = question?.optionOne
        tvOption2?.text = question?.optionTwo
        tvOption3?.text = question?.optionThree
        tvOption4?.text = question?.optionFour

        if(mCurrentPosition == mQuestionsList?.size){
            btnSubmit?.text = "FINISH"
        } else{
            btnSubmit?.text = "SUBMIT"
        }
    }

    private fun defaultOptionsVIew(){
        val options = ArrayList<TextView>()
        tvOption1?.let {
            options.add(0,it)
        }
        tvOption2?.let {
            options.add(1,it)
        }
        tvOption3?.let {
            options.add(2,it)
        }
        tvOption4?.let {
            options.add(3,it)
        }

        for(temp in options){
            temp.setTextColor(Color.parseColor("#7A8089"))
            temp.typeface = Typeface.DEFAULT
            temp.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectOptionView(tv: TextView, selectOptionNum: Int){
        defaultOptionsVIew()
        mSelectedOptionPosition = selectOptionNum
        tv.setTextColor((Color.parseColor("#363A43")))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.select_option_border_bg)
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> {
                tvOption1?.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                tvOption2?.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                tvOption3?.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                tvOption4?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnSubmit ->{
                when(mBtnStatus){
                    0 -> {
                        if(mSelectedOptionPosition == null){
                            Toast.makeText(this, "Please select your answer.", Toast.LENGTH_LONG).show()
                        } else{
                            var question = mQuestionsList!![mCurrentPosition-1]
                            var key = question.correctAnswer
                            answerView(key, R.drawable.correct_option_border_bg)
                            if(key != mSelectedOptionPosition!!+1){
                                answerView(mSelectedOptionPosition!! +1, R.drawable.wrong_option_border_bg )
                                mWrongCount++
                            }

                            if (mCurrentPosition == 10){
                                btnSubmit?.text = "FINISH"
                                mBtnStatus = 2
                            } else{
                                btnSubmit?.text = "GO TO NEXT QUESTION"
                                mBtnStatus = 1
                            }
                        }
                    }
                    1 ->{
                        mSelectedOptionPosition = null
                        defaultOptionsVIew()
                        setUI(++mCurrentPosition)
                        mBtnStatus = 0
                        btnSubmit?.text = "SUBMIT"

                    }
                    2 ->{
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, mUsername)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, (mQuestionsList!!.size ))
                        intent.putExtra(Constants.CORRECT_ANSWER, (mQuestionsList!!.size - mWrongCount) as Int)
                        startActivity(intent)
                        finish()
                    }
                }

            }
            R.id.tvOption1 ->{
                tvOption1?.let {
                    selectOptionView(it, 0)
                }
            }
            R.id.tvOption2 ->{
                tvOption2?.let {
                    selectOptionView(it, 1)
                }
            }
            R.id.tvOption3 ->{
                tvOption3?.let {
                    selectOptionView(it, 2)
                }
            }
            R.id.tvOption4 ->{
                tvOption4?.let {
                    selectOptionView(it, 3)
                }
            }

        }

    }


}