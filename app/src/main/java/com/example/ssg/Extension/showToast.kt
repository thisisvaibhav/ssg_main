package com.example.ssg.Extension

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.ssg.R
import com.example.ssg.apiManager.ProgressDialogUtils
import com.google.android.material.snackbar.Snackbar


/*---------- Toast ----------*/

fun AppCompatActivity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showToast(@StringRes resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
}


/*---------- Progress Dialog ----------*/

fun AppCompatActivity.showProgressDialog() {
    showProgressDialog("Loading...")
}

fun AppCompatActivity.showProgressDialog(message: String) {
    ProgressDialogUtils.getInstance()
            .create(this)
            .withMessage(message)
            .cancelable(false)
            .show()
}

fun AppCompatActivity.hideProgressDialog() {
    ProgressDialogUtils.getInstance()
            .hide()
}
/*---------- No Internet----------*/

fun AppCompatActivity.showInternetError() {
    showSnackBar(getString(R.string.err_internet))
}


private fun AppCompatActivity.showSnackBar(message: String) {
    val snackbar = Snackbar.make(
            findViewById(android.R.id.content),
            message, Snackbar.LENGTH_SHORT
    )
    val sbView = snackbar.view
    val textView = sbView
            .findViewById<View>(R.id.snackbar_text) as TextView
    textView.setTextColor(ContextCompat.getColor(this, R.color.black))
    snackbar.show()
}