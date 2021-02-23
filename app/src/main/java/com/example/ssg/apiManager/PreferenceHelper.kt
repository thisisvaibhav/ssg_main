package com.example.ssg.apiManager

import android.content.Context
import android.content.SharedPreferences
import com.example.ssg.AppClass

class PreferenceHelper private constructor() {


    private val IS_LOGIN = "IS_LOGIN"
    private val IS_GOOGLE_LOGIN = "IS_GOOGLE_LOGIN"
    private val PHONE_NUMBER = "PHONE_NUMBER"
    private val EMAIL = "EMAIL"
    private val USER_IMAGE="PHOTO"
    private val USERNAME="NAME"
    private val COUNTRY="COUNTRY"
    private val mPrefs: SharedPreferences

    init {
        val application = AppClass.getInstance()
        mPrefs = application.getSharedPreferences("SecPass", Context.MODE_PRIVATE)
    }

    companion object {
        private var instance: PreferenceHelper? = null

        @JvmStatic
        fun getInstance(): PreferenceHelper {
            if (instance == null) {
                instance = PreferenceHelper()
            }
            return instance as PreferenceHelper
        }
    }


    var isLogin: Boolean?
        get() = mPrefs.getBoolean(IS_LOGIN, false)
        set(isLogin) = mPrefs.edit().putBoolean(IS_LOGIN, isLogin!!).apply()

    var isGoogleLogin: Boolean?
        get() = mPrefs.getBoolean(IS_GOOGLE_LOGIN, false)
        set(isGoogleLogin) = mPrefs.edit().putBoolean(IS_LOGIN, isGoogleLogin!!).apply()

    var Username:String?
        get() = mPrefs.getString(USERNAME,"")
    set(Username) = mPrefs.edit().putString(USERNAME,Username!!).apply()

    var UserImage:String?
        get() = mPrefs.getString(USER_IMAGE,"")
        set(UserImage) = mPrefs.edit().putString(USER_IMAGE,UserImage!!).apply()

    var Email:String?
        get() = mPrefs.getString(EMAIL,"")
        set(Email) = mPrefs.edit().putString(USER_IMAGE,Email!!).apply()

    var PhoneNumer:String?
        get() = mPrefs.getString(PHONE_NUMBER,"")
        set(PhoneNumber) = mPrefs.edit().putString(USER_IMAGE,PhoneNumber!!).apply()

    var Country:String?
        get() = mPrefs.getString(COUNTRY,"")
    set(Country) = mPrefs.edit().putString(COUNTRY,Country!!).apply()

    fun clearPref() {

        mPrefs.edit().remove(IS_LOGIN).apply()
        mPrefs.edit().remove(IS_GOOGLE_LOGIN).apply()
        mPrefs.edit().remove(EMAIL).apply()
        mPrefs.edit().remove(PHONE_NUMBER).apply()
        mPrefs.edit().remove(USER_IMAGE).apply()
        mPrefs.edit().remove(COUNTRY).apply()
        mPrefs.edit().remove(USER_IMAGE).apply()
    }
}