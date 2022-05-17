package com.example.darren.quizapp

data class Question(
    val id: Int,
    val description: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int
)
