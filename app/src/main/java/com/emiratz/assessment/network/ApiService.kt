package com.emiratz.assessment.network

import com.emiratz.assessment.model.LoginRequest
import com.emiratz.assessment.model.LoginResponse
import com.emiratz.assessment.model.RegisterRequest
import com.emiratz.assessment.model.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

    @POST("api/auth/login")
    fun loginData(@Body loginRequest: LoginRequest) : Call<LoginResponse>

    @POST("api/auth/register")
    fun registerData(@Body registerRequest: RegisterRequest) : Call<RegisterResponse>

}