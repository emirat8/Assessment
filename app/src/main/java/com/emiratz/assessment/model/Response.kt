package com.emiratz.assessment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseGetAllData(
    @field:SerializedName("data")
    val data: List<AssessmentDetailResponse?>? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("status")
    val status: Int? = null
) : Parcelable
@Parcelize
data class AssessmentDetailResponse(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("endDate")
    val endDate: String? = null,

    @field:SerializedName("questions")
    val questions: List<QuestionResponse?>? = null,
) : Parcelable
@Parcelize
data class QuestionResponse(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("text")
    val text: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("choices")
    val choices: List<ChoiceResponse?>? = null,

    var selectedChoice: Int? = -1 // Properti untuk melacak pilihan yang dipilih

) : Parcelable

@Parcelize
data class ChoiceResponse(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("value")
    val value: String? = null,

    @field:SerializedName("true")
    val isTrue: Boolean? = false,
) : Parcelable
@Parcelize
data class AssessmentResultResponse(
    @field:SerializedName("data")
    val data: List<AssessmentResultDetailResponse?>? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("status")
    val status: Int? = null
) : Parcelable
@Parcelize
data class AssessmentResultDetailResponse(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("takeDate")
    val takeDate: String? = null,

    @field:SerializedName("score")
    val score: Int? = null
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
    val nama: String? = null,
) : Parcelable

@Parcelize
data class RegisterResponse(
    @field:SerializedName("message")
    val message: String? = null,
) : Parcelable

object AssessmentResultDummy{
    var dataAssessmentResult = AssessmentResultResponse(
        data = arrayListOf<AssessmentResultDetailResponse>(
            AssessmentResultDetailResponse(
                id = 1,
                title = "Assessment Pertama",
                takeDate = "2023-10-13",
                score = 85
            ),
            AssessmentResultDetailResponse(
                id = 2,
                title = "Assessment Kedua",
                takeDate = "2023-10-16",
                score = 90
            )
        )
    )
}

object AssessmentDummy{
    var dataAssessment = arrayListOf<ResponseGetAllData>(
        ResponseGetAllData(
            data = arrayListOf(
                AssessmentDetailResponse(
                    id = 1,
                    title = "Assessment Pertama",
                    endDate = "2023-10-13",
                    questions = arrayListOf(
                        QuestionResponse(
                            id = 1,
                            text = "Question 1",
                            image = null,
                            choices = arrayListOf(
                                ChoiceResponse(
                                    id = 1,
                                    value = "a",
                                    isTrue = false
                                ),
                                ChoiceResponse(
                                    id = 2,
                                    value = "b",
                                    isTrue = false
                                ),
                                ChoiceResponse(
                                    id = 3,
                                    value = "c",
                                    isTrue = true
                                ),
                                ChoiceResponse(
                                    id = 4,
                                    value = "d",
                                    isTrue = false
                                )
                            )
                        ),
                        QuestionResponse(
                            id = 2,
                            text = "Question 2",
                            image = null,
                            choices = arrayListOf(
                                ChoiceResponse(
                                    id = 5,
                                    value = "a",
                                    isTrue = false
                                ),
                                ChoiceResponse(
                                    id = 6,
                                    value = "b",
                                    isTrue = false
                                ),
                                ChoiceResponse(
                                    id = 7,
                                    value = "c",
                                    isTrue = true
                                ),
                                ChoiceResponse(
                                    id = 8,
                                    value = "d",
                                    isTrue = false
                                )
                            )
                        )
                    )
                )
            )
        )
    )
}

data class UserData(
    val token: String,
    val userId: Int)