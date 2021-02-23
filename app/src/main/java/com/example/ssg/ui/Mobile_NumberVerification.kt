package com.example.ssg.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ssg.Extension.hideProgressDialog
import com.example.ssg.Extension.showProgressDialog
import com.example.ssg.Extension.showToast
import com.example.ssg.Model.SendOtpData
import com.example.ssg.R
import com.example.ssg.apiManager.PreferenceHelper
import com.example.ssg.apiManager.RemoteCallback
import com.example.ssg.apiManager.WebApiManager
import kotlinx.android.synthetic.main.activity_mobile__number_verification.*

class Mobile_NumberVerification:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile__number_verification)
        countryPicker.selectedCountryCode


        verification_button.setOnClickListener {
            nullcheck()
            sendOtp()
        }
    }

    private fun nullcheck():Boolean {
        return if (number.text.toString().isEmpty()||number.text.toString().length!=10){
            showToast("Please Enter Phone Number")
            false
        }
        else{
            sendOtp()
            true
        }
    }

    private fun sendOtp() {
        showProgressDialog()
        WebApiManager
                .instance
                .sendMsg(countryPicker.selectedCountryCode,number.text.toString())
                .enqueue(object :RemoteCallback<SendOtpData>(){
                    override fun onSuccess(response: SendOtpData?) {
                        PreferenceHelper.getInstance().PhoneNumer=number.text.toString()
                        PreferenceHelper.getInstance().Country=countryPicker.selectedCountryCode
                        hideProgressDialog()
                        showToast(response!!.message)
                        val intent = Intent(this@Mobile_NumberVerification,Verification_Screen::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
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