package com.example.ssg.Extension

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.ssg.R
import com.example.ssg.apiManager.ProgressDialogUtils
import com.google.android.material.snackbar.Snackbar

/*---------- Progress Dialog ----------*/

fun Fragment.showProgressDialog() {
    showProgressDialog("Loading...")
}

fun Fragment.showProgressDialog(message: String) {
    ProgressDialogUtils.getInstance()
            .create(context!!)
            .withMessage(message)
            .cancelable(false)
            .show()
}

fun Fragment.hideProgressDialog() {
    ProgressDialogUtils.getInstance()
            .hide()
}

/*---------- Toast ----------*/

fun Fragment.showToast(msg: String) {
    try {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {

    }
}

fun Fragment.showToast(@StringRes resId: Int) {
    Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
}

/*---------- No Internet----------*/

fun Fragment.showInternetError() {
    showSnackBar(getString(R.string.err_internet))
}


private fun Fragment.showSnackBar(message: String) {
    val snackbar = Snackbar.make(
            requireActivity().findViewById(android.R.id.content)!!,
            message, Snackbar.LENGTH_SHORT
    )
    val sbView = snackbar.view
    val textView = sbView
            .findViewById<View>(R.id.snackbar_text) as TextView
    textView.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
    snackbar.show()
}