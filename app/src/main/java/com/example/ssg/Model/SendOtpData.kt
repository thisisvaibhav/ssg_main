package com.example.ssg.Model

data class SendOtpData(
    val message: String,
    val status: Int,
    val success: Boolean
)