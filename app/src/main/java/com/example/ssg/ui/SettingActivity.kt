package com.example.ssg.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ssg.R
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initView()
    }

    private fun initView() {
        tvAccount.setOnClickListener {

        }
    }
}