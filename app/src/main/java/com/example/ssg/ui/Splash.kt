package com.example.ssg.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.ssg.R
import com.example.ssg.apiManager.PreferenceHelper

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        Handler().postDelayed({
            if (PreferenceHelper.getInstance().isLogin!! || PreferenceHelper.getInstance().isGoogleLogin!!) { //How can I ask here?
                val intentMain = Intent(this, HomeActivity::class.java)
                intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intentMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intentMain)
            } else if (PreferenceHelper.getInstance().Email==""&& PreferenceHelper.getInstance().isLogin!! ) {

                val intentDetail = Intent(this, UserDetailsActivity::class.java)
                intentDetail.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intentDetail.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intentDetail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intentDetail)
            }
            else if (PreferenceHelper.getInstance().Email==""&& PreferenceHelper.getInstance().isGoogleLogin!! ) {

                val intentDetail = Intent(this, UserDetailsActivity::class.java)
                intentDetail.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intentDetail.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intentDetail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intentDetail)
            }
            else {
                val intent = Intent(this, SignInActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            finish()
        }, 3000.toLong())

    }
}