package com.emiratz.assessment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginRequest(
    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("password")
    val password: String? = null
) : Parcelable

@Parcelize
data class RegisterRequest(
    @field:SerializedName("nama")
    val nama: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("password")
    val password: String? = null
) : Parcelable

@Parcelize
data class LoginResponse(
    @field:SerializedName("token")
    val token: String? = null,
) : Parcelable

@Parcelize
data class RegisterResponse(
    @field:SerializedName("message")
    val message: String? = null,
) : Parcelable