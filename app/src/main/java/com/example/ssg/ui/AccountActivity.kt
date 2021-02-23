package com.example.ssg.ui

import android.app.Activity
import android.os.Bundle
import com.example.ssg.R
import com.example.ssg.apiManager.PreferenceHelper
import kotlinx.android.synthetic.main.activity_account.*

class AccountActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        initView()
    }

    private fun initView() {
        logout.setOnClickListener {
            PreferenceHelper.getInstance().clearPref()
        }
    }
}