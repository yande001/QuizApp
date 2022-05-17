package com.example.darren.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btnStart)
        val inputName: EditText = findViewById(R.id.editName)

        btnStart.setOnClickListener {
            if (inputName.text.isEmpty()){
                Toast.makeText(this, "Please enter your name.", Toast.LENGTH_LONG).show()
            } else{
                val intent = Intent(this, QuestionActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}