package com.emiratz.assessment.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emiratz.assessment.R
import com.emiratz.assessment.adapter.QuestionAdapter
import com.emiratz.assessment.model.AnswerDetail
import com.emiratz.assessment.model.Choice
import com.emiratz.assessment.model.Question
import com.emiratz.assessment.model.QuestionRequest
import com.emiratz.assessment.model.QuestionResponse
import com.emiratz.assessment.model.ResponseNoData
import com.emiratz.assessment.network.ApiConfig
import com.emiratz.assessment.util.DataStoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionFragment(val listQuestion : List<QuestionResponse?>?, val endDate: String, val assessmentId : Int?) : Fragment(), CoroutineScope {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var rvQuestion: RecyclerView
    private lateinit var questionAdapter : QuestionAdapter
    lateinit var submitButton: Button
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var job: Job
    private lateinit var txtCountDown: TextView

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    fun parseIsoDateToMilliseconds(isoDate: String): Long {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val instant = Instant.from(formatter.parse(isoDate))
        return instant.toEpochMilli()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvQuestion = view.findViewById(R.id.rvQuestion)
        submitButton = view.findViewById(R.id.btnSubmit)
        txtCountDown = view.findViewById(R.id.txtCountDown)

        getAllQuestion()

        val endMilli = parseIsoDateToMilliseconds(endDate)
        val startMilli = Instant.now().toEpochMilli()


        val timer = object: CountDownTimer(endMilli - startMilli, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60
                val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)

                // Update the TextView
                val formattedTime = String.format("%02d : %02d : %02d", hours, minutes, seconds)
                txtCountDown.text = formattedTime
            }

            override fun onFinish() {

            }
        }

        timer.start()

        submitButton.setOnClickListener{
            launch {
                dataStoreManager = DataStoreManager(requireContext())
                val token = dataStoreManager.getTokenValue()
                Log.i("tokennya", token)
                sendAnswer(token)
            }
        }
    }
    fun sendAnswer(token:String) {
        val selectedQuestions = mutableListOf<QuestionResponse>()

        if (listQuestion != null) {
            for (question in listQuestion) {
                if (question?.selectedChoice != -1) {
                    if (question != null) {
                        selectedQuestions.add(question)
                    }
                } else {
                    Toast.makeText(requireContext(), "Pilih semua opsi", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val questionRequests = ArrayList<AnswerDetail?>()

        for (selectedQuestion in selectedQuestions) {
            val answerDetail = AnswerDetail(
                Question(selectedQuestion?.id),
                Choice(selectedQuestion?.selectedChoice)
            )
            questionRequests.add(answerDetail)
        }

        val client = ApiConfig.getApiService()
            .answerData("Bearer $token",
                assessmentId,
                QuestionRequest(questionRequests)
            )

        Log.i("SEND ANSWER", QuestionRequest(questionRequests).toString())

        client.enqueue(object : Callback<ResponseNoData> {
            override fun onResponse(
                call: Call<ResponseNoData>,
                response: Response<ResponseNoData>
            ) {
                Log.i("SEND ANSWER", response.toString())
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    Log.i("SEND ANSWER", "onSuccess: ${responseBody.data}")
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frmFragmentRoot, HomeFragment())
                        .commit()
                }
            }
            override fun onFailure(call: Call<ResponseNoData>, t: Throwable) {
                Log.e("SEND ANSWER", "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getAllQuestion() {
        questionAdapter = QuestionAdapter(listQuestion, requireActivity())
        rvQuestion.layoutManager = LinearLayoutManager(context)
        rvQuestion.adapter = questionAdapter
    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment QuestionFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: List<QuestionResponse?>?, param2: String) =
//            QuestionFragment().apply {
//                arguments = Bundle().apply {
//                    put(ARG_PARAM1, arrayListOf(param1))
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}