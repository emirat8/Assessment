package com.emiratz.assessment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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
    @field:SerializedName("endDate")
    val endDate: String? = null,
    @field:SerializedName("questions")
    val questions: List<QuestionResultResponse?>? = null,
    @field:SerializedName("results")
    val results: List<ResultResponse?>? = null
) : Parcelable

@Parcelize
data class ResultResponse(
    @field:SerializedName("finalScore")
    val finalScore: Int? = null,
    @field:SerializedName("user")
    val user: UserResultResponse? = null
) : Parcelable

@Parcelize
data class UserResultResponse(
    @field:SerializedName("id")
    val id: Int? = null,
) : Parcelable

@Parcelize
data class QuestionResultResponse(
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("text")
    val text: String? = null,
    @field:SerializedName("image")
    val image: String? = null,
    @field:SerializedName("choices")
    val choices: List<ChoiceResultResponse?>? = null,
    var selectedChoice: Int? = -1 // Properti untuk melacak pilihan yang dipilih
) : Parcelable
@Parcelize
data class ChoiceResultResponse(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("value")
    val value: String? = null,

    @field:SerializedName("true")
    val isTrue: Boolean? = false,
) : Parcelable
@Parcelize
data class QuestionResultRequest(
    @field:SerializedName("answers")
    val data: List<AnswerResultDetail?>? = null,
) : Parcelable

@Parcelize
data class AnswerResultDetail(
    @field:SerializedName("question")
    val question: QuestionResult? = null,
    @field:SerializedName("choice")
    val choice: ChoiceResult? = null,
) : Parcelable

@Parcelize
data class QuestionResult(
    @field:SerializedName("id")
    val id: Int? = null,
) : Parcelable
@Parcelize
data class ChoiceResult(
    @field:SerializedName("id")
    val id: Int? = null,
) : Parcelable