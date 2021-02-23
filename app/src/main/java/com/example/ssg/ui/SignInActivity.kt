package com.example.ssg.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ssg.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initView()
    }

    private fun initView() {
        tvSignIn.setOnClickListener {
            startActivity(Intent(this,SignActivity::class.java))
        }
        newaccount.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

}
