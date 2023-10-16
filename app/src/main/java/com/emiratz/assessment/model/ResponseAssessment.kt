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
data class ParticipantResponse(
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("results")
    val results: List<ResultResponse?>? = null,
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
data class QuestionRequest(
    @field:SerializedName("answers")
    val data: List<AnswerDetail?>? = null,
) : Parcelable

@Parcelize
data class AnswerDetail(
    @field:SerializedName("question")
    val question: Question? = null,
    @field:SerializedName("choice")
    val choice: Choice? = null,
) : Parcelable

@Parcelize
data class Question(
    @field:SerializedName("id")
    val id: Int? = null,
) : Parcelable
@Parcelize
data class Choice(
    @field:SerializedName("id")
    val id: Int? = null,
) : Parcelable