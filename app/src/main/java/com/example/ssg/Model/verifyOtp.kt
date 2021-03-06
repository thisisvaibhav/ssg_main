package com.example.ssg.Model

data class verifyOtp(
    val countryCode: String,
    val message: String,
    val phone: String,
    val status: Int,
    val success: Boolean,
    val user: User
)

data class User(
    val __v: Int,
    val _id: String,
    val bookmarks: List<Any>,
    val countrycode: String,
    val createdAt: String,
    val dob: String,
    val email: String,
    val fname: String,
    val followers: List<Any>,
    val following: List<Any>,
    val gender: String,
    val height: Double,
    val lname: String,
    val phone: String,
    val photos: String,
    val token: String,
    val type: String,
    val updatedAt: String,
    val username: String,
    val weight: Int
)