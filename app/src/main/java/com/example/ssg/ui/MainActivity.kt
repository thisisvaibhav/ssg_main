package com.example.ssg.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ssg.Extension.showToast
import com.example.ssg.R
import com.example.ssg.apiManager.PreferenceHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val RC_SIGN_IN=234
    private var firebaseAuth: FirebaseAuth?=null
    private var mAuth: FirebaseAuth? = null
    var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        mobile_no.setOnClickListener {
            startActivity(Intent(this,Mobile_NumberVerification::class.java))
        }
        mAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.webid))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        gmail.setOnClickListener {
            showToast("working")
            signin()
        }
    }

    private fun signin() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //if the requestCode is the Google Sign In code that we defined at starting
        if (requestCode == RC_SIGN_IN) {

            //Getting the GoogleSignIn Task
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                //Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                //authenticating with firebase
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this,"nothing",Toast.LENGTH_LONG).show()
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + account!!.getId())

        val credential = GoogleAuthProvider.getCredential(account.getIdToken(), null)

        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG", "signInWithCredential:success")
                        val user = mAuth!!.currentUser
                        PreferenceHelper.getInstance().Email=user!!.email
                        PreferenceHelper.getInstance().UserImage
                        PreferenceHelper.getInstance().Username=user.displayName
                        PreferenceHelper.getInstance().isGoogleLogin=true
                        Toast.makeText(this@MainActivity, "User Signed In", Toast.LENGTH_SHORT).show()
                        var signintent =Intent(this,UserDetailsActivity::class.java)
                    } else {
                        Log.w("TAG", "signInWithCredential:failure", task.exception)
                        Toast.makeText(this@MainActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                    }

                }
    }

}