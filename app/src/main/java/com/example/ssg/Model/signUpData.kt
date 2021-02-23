package com.example.ssg.Model

data class signUpData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val user: ArrayList<Userdata>
)

data class Userdata(
    val __v: Int,
    val _id: String,
    val countrycode: String,
    val createdAt: String,
    val dob: String,
    val email: String,
    val fname: String,
    val followers: Any,
    val following: Any,
    val gender: String,
    val height: Int,
    val lname: String,
    val phone: String,
    val photos: String,
    val token: String,
    val type: String,
    val updatedAt: String,
    val username: String,
    val weight: Int
)