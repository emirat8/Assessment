package com.emiratz.assessment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseNoData(
    @field:SerializedName("data")
    val data: String? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("status")
    val status: Int? = null
) : Parcelable

@Parcelize
data class LoginResponse(
    @field:SerializedName("data")
    val data: UserDataLoginResponse? = null,
) : Parcelable

@Parcelize
data class UserDataLoginResponse(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("token")
    val token: String? = null,
) : Parcelable

@Parcelize
data class LoginRequest(
    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("password")
    val password: String? = null
) : Parcelable

@Parcelize
data class RegisterRequest(
    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("name")
    val name: String? = null
) : Parcelable

@Parcelize
data class RegisterResponse(
    @field:SerializedName("message")
    val message: String? = null,
) : Parcelable

@Parcelize
data class MessageOnlyResponse(
    @field:SerializedName("message")
    val message: String? = null,
) : Parcelable

//object AssessmentResultDummy{
//    var dataAssessmentResult = AssessmentResultResponse(
//        data = arrayListOf<AssessmentResultDetailResponse>(
//            AssessmentResultDetailResponse(
//                id = 1,
//                title = "Assessment Pertama",
//                takeDate = "2023-10-13",
//                score = 85
//            ),
//            AssessmentResultDetailResponse(
//                id = 2,
//                title = "Assessment Kedua",
//                takeDate = "2023-10-16",
//                score = 90
//            )
//        )
//    )
//}
//
//object AssessmentDummy{
//    var dataAssessment = arrayListOf<ResponseGetAllData>(
//        ResponseGetAllData(
//            data = arrayListOf(
//                AssessmentDetailResponse(
//                    id = 1,
//                    title = "Assessment Pertama",
//                    endDate = "2023-10-13",
//                    questions = arrayListOf(
//                        QuestionResponse(
//                            id = 1,
//                            text = "Question 1",
//                            image = null,
//                            choices = arrayListOf(
//                                ChoiceResponse(
//                                    id = 1,
//                                    value = "a",
//                                    isTrue = false
//                                ),
//                                ChoiceResponse(
//                                    id = 2,
//                                    value = "b",
//                                    isTrue = false
//                                ),
//                                ChoiceResponse(
//                                    id = 3,
//                                    value = "c",
//                                    isTrue = true
//                                ),
//                                ChoiceResponse(
//                                    id = 4,
//                                    value = "d",
//                                    isTrue = false
//                                )
//                            )
//                        ),
//                        QuestionResponse(
//                            id = 2,
//                            text = "Question 2",
//                            image = null,
//                            choices = arrayListOf(
//                                ChoiceResponse(
//                                    id = 5,
//                                    value = "a",
//                                    isTrue = false
//                                ),
//                                ChoiceResponse(
//                                    id = 6,
//                                    value = "b",
//                                    isTrue = false
//                                ),
//                                ChoiceResponse(
//                                    id = 7,
//                                    value = "c",
//                                    isTrue = true
//                                ),
//                                ChoiceResponse(
//                                    id = 8,
//                                    value = "d",
//                                    isTrue = false
//                                )
//                            )
//                        )
//                    )
//                )
//            )
//        )
//    )
//}

data class UserData(
    val token: String,
    val userId: Int)