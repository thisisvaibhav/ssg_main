package com.example.ssg.apiManager

import com.example.ssg.Model.SendOtpData
import com.example.ssg.Model.VerifyUser
import com.example.ssg.Model.signUpData
import com.example.ssg.Model.verifyOtp
import retrofit2.Call

class WebApiManager private constructor() {

    private val mApiServices: WebAPIService = WebAPISeviceFactory.newInstance().makeServiceFactory()

    companion object {

        private var INSTANCE: WebApiManager? = null

        val instance: WebApiManager
            get() {
                if (INSTANCE == null) {
                    INSTANCE = WebApiManager()
                }
                return INSTANCE as WebApiManager
            }
    }

    fun sendMsg(country: String, phone: String): Call<SendOtpData> {
        return mApiServices.sendMsg(country, phone)
    }

    fun verifyOtp(country: String, phone: String, code: String): Call<verifyOtp> {
        return mApiServices.verifyOtp(country, phone, code)
    }

    fun signUpUser(type: String, fname: String,
                   lname: String, username: String,
                   dob: String, countrycode: String,
                   phone: String, email: String,
                   weight: String, height: String,
                   token: String): Call<signUpData> {
        return mApiServices.signUpUser(type, fname, lname, username, dob, countrycode, phone, email, weight,
                height, token, "")
    }

    fun checkPhone(countryCode:String,phone: String) : Call<VerifyUser>{
        return mApiServices.checkPhone(countryCode,phone)
    }

}