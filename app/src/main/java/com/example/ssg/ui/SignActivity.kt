package com.example.ssg.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ssg.Extension.hideProgressDialog
import com.example.ssg.Extension.showProgressDialog
import com.example.ssg.Extension.showToast
import com.example.ssg.Model.VerifyUser
import com.example.ssg.R
import com.example.ssg.apiManager.RemoteCallback
import com.example.ssg.apiManager.WebApiManager
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        initView()
    }

    private fun initView() {
        tvEnter.setOnClickListener {
            if (validateMethod()){
                apiCall()
            }
        }
    }

    private fun apiCall() {
        showProgressDialog()
        WebApiManager
                .instance
                .checkPhone(
                        verifyCountryCode.selectedCountryCode,
                        etPhone.text.toString()
                )
                .enqueue(object : RemoteCallback<VerifyUser>(){
                    override fun onSuccess(response: VerifyUser?) {
                        hideProgressDialog()
                        showToast(response!!.message)
                        startActivity(Intent(this@SignActivity,Verification_Screen::class.java))
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

    private fun validateMethod():Boolean {
        if (etPhone.text.toString().isNullOrEmpty()){
            showToast("Please Enter Phone Number")
            return false
        }
        else if (etPhone.text.toString().length!=10){
            showToast("Please Enter Correct Phone Number")
            return false
        }
        else{
            return true
        }
    }
}