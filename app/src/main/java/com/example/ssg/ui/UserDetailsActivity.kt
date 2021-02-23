package com.example.ssg.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.example.ssg.Extension.hideProgressDialog
import com.example.ssg.Extension.showProgressDialog
import com.example.ssg.Extension.showToast
import com.example.ssg.Model.signUpData
import com.example.ssg.R
import com.example.ssg.apiManager.PreferenceHelper
import com.example.ssg.apiManager.RemoteCallback
import com.example.ssg.apiManager.WebApiManager
import kotlinx.android.synthetic.main.activity_user_details.*
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS
import kotlin.collections.ArrayList


class UserDetailsActivity : AppCompatActivity() {
    var spinnerArray: ArrayList<String> = arrayListOf()
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    var pos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        date.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                date.text = "$dayOfMonth $monthOfYear, $year"

            }, year, month, day)
            dpd.show()
        }
        spinnerArray.clear()
        spinnerArray.add("Male")
        spinnerArray.add("Female")
        spinnerArray.add("Other")
//        spinner_gender.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArray)
        tvSubmit.setOnClickListener {
            if(Check()){
                apiCall()
            }
        }
//        spinner_gender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                pos = position
//
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                spinner_gender.prompt = "Select Gender"
//            }
//        }
    }

    private fun apiCall() {
        showProgressDialog()
        WebApiManager.instance.signUpUser("",
                firstname.text.toString(),
                lastname.text.toString(),
                username.text.toString(),
                date.text.toString(),
                countryPicker2.selectedCountryCode,
                number.text.toString(),
                email.text.toString(),
                height.text.toString(),
                weight.text.toString(),
                ""
        ).enqueue(object : RemoteCallback<signUpData>() {
            override fun onSuccess(response: signUpData?) {
                hideProgressDialog()
                if (PreferenceHelper.getInstance().Email == null) {
                    PreferenceHelper.getInstance().Username = response!!.user[0].username
                    PreferenceHelper.getInstance().PhoneNumer = response.user[0].phone
                    PreferenceHelper.getInstance().isLogin = true
                    PreferenceHelper.getInstance().Country = response.user[0].countrycode
                }
                val intent = Intent(this@UserDetailsActivity, HomeActivity::class.java)
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

    private fun Check(): Boolean {
        if (firstname.text.toString().isEmpty()
                || lastname.text.toString().isEmpty()
                || username.text.toString().isEmpty()
                || date.text.toString().isEmpty()
                || number.text.toString().isEmpty()
                || weight.text.toString().isEmpty()
                || height.text.toString().isEmpty()

        ) {
            return false
        }
        return true
    }

}