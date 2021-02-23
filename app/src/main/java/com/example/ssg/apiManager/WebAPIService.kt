package com.example.ssg.apiManager

import com.example.ssg.Model.SendOtpData
import com.example.ssg.Model.VerifyUser
import com.example.ssg.Model.signUpData
import com.example.ssg.Model.verifyOtp
import retrofit2.Call
import retrofit2.http.*

interface WebAPIService {

    @GET("phone")
    fun sendMsg(
            @Query("country") country:String,
            @Query("phone") phone:String
    ): Call<SendOtpData>

    @GET("verify")
    fun verifyOtp(
            @Query("country") country: String,
            @Query("phone") phone:String,
            @Query("code") code:String
    ):Call<verifyOtp>

    @FormUrlEncoded
    @POST("signup")
    fun signUpUser(
            @Field("type") type: String,
            @Field("fname") fname:String,
            @Field("lname") lname:String,
            @Field("username") username:String,
            @Field("dob") dob:String,
            @Field("countrycode") countrycode:String,
            @Field("phone") phone:String,
            @Field("email") email:String,
            @Field("weight") weight:String,
            @Field("height") height:String,
            @Field("token") token:String,
            @Field("photos") photos:String,
    ):Call<signUpData>

    @FormUrlEncoded
    @POST("register")
    fun checkPhone(
            @Field("countryCode")countryCode:String,
            @Field("phone") phone : String
    ) :Call<VerifyUser>

}

