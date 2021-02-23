package com.example.ssg.ui

import `in`.aabhasjindal.otptextview.OTPListener
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.ssg.Extension.hideProgressDialog
import com.example.ssg.Extension.showProgressDialog
import com.example.ssg.Extension.showToast
import com.example.ssg.Model.verifyOtp
import com.example.ssg.R
import com.example.ssg.apiManager.PreferenceHelper
import com.example.ssg.apiManager.RemoteCallback
import com.example.ssg.apiManager.WebApiManager
import kotlinx.android.synthetic.main.activity_varification__screen.*


class Verification_Screen:AppCompatActivity() {
    var fullOtp:String?=null
    var REQUEST_CODE_READ_SMS=121
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_varification__screen)
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECEIVE_SMS), REQUEST_CODE_READ_SMS)

        initView()
        phonenuber.text = PreferenceHelper.getInstance().PhoneNumer

    }

    private fun initView() {
        otp_view.otpListener = object : OTPListener {
            override fun onInteractionListener() {

            }
            override fun onOTPComplete(otp: String) {
                fullOtp=otp
            }
        }
        tvNext.setOnClickListener {
            showProgressDialog()
            WebApiManager
                    .instance
                    .verifyOtp(
                            PreferenceHelper.getInstance().Country!!,
                            PreferenceHelper.getInstance().PhoneNumer!!,
                            fullOtp!!
                            )
                    .enqueue(object :RemoteCallback<verifyOtp>(){
                        override fun onSuccess(response: verifyOtp?) {
                            hideProgressDialog()
                            showToast(response!!.message)
                            if (response.user.email!=""){
                                val intent=Intent(this@Verification_Screen,HomeActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            }
                            else{
                                val intent=Intent(this@Verification_Screen,UserDetailsActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            }
                        }

                        override fun onUnauthorized(throwable: Throwable) {
                            hideProgressDialog()
                            showToast(throwable.message!!)
                        }

                        override fun onFailed(throwable: Throwable) {
                            hideProgressDialog()
                            showToast(throwable.message!!)
                        }

                        override fun onInternetFailed() {
                            hideProgressDialog()
                            onInternetFailed()
                        }

                        override fun onEmptyResponse(message: String) {
                            hideProgressDialog()
                            showToast(message)
                        }
                    })
        }
    }
}