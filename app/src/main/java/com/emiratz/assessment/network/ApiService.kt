package com.emiratz.assessment.network

import com.emiratz.assessment.model.AssessmentResultResponse
import com.emiratz.assessment.model.LoginRequest
import com.emiratz.assessment.model.LoginResponse
import com.emiratz.assessment.model.QuestionRequest
import com.emiratz.assessment.model.QuestionResponse
import com.emiratz.assessment.model.RegisterRequest
import com.emiratz.assessment.model.RegisterResponse
import com.emiratz.assessment.model.ResponseGetAllData
import com.emiratz.assessment.model.ResponseNoData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("api/auth/login")
    fun loginData(@Body loginRequest: LoginRequest) : Call<LoginResponse>

    @POST("api/auth/register")
    fun registerData(@Body registerRequest: RegisterRequest) : Call<RegisterResponse>

    @GET("api/assessment/user/{userId}/")
    fun getAllAssessment(@Header("Authorization") authToken: String, @Path("userId") userId: Int) : Call<ResponseGetAllData>

    @GET("api/assessment/user/{userId}/akwoakwoak")
    fun getAllAssessmentResult(@Header("Authorization") authToken: String, @Path("userId") userId: Int) : Call<AssessmentResultResponse>

    @POST("api/assessment/{assessmentId}/result")
    fun answerData(@Header("Authorization") authToken: String, @Path("assessmentId") assessmentId: Int?, @Body questionRequest: QuestionRequest) : Call<ResponseNoData>

}