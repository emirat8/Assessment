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
    val choices: List<String?>? = null
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

//object AssessmentDummy{
//    var dataAssessment = arrayListOf<AssessmentResponse>(
//        AssessmentResponse(
//            data = AssessmentDetailResponse(
//                id = 1,
//                title = "Assessment Pertama",
//                closeDate = "2023-10-13"
//            )
//        ),
//        AssessmentResponse(
//            data = AssessmentDetailResponse(
//                id = 2,
//                title = "Assessment Kedua",
//                closeDate = "2023-10-17"
//            )
//        ),
//        AssessmentResponse(
//            data = AssessmentDetailResponse(
//                id = 3,
//                title = "Assessment Ketiga",
//                closeDate = "2023-10-25"
//            )
//        )
//    )
//}

data class UserData(
    val token: String,
    val userId: Int)