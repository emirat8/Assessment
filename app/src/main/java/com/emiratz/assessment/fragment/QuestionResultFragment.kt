package com.emiratz.assessment.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emiratz.assessment.R
import com.emiratz.assessment.adapter.QuestionAdapter
import com.emiratz.assessment.adapter.QuestionResultAdapter
import com.emiratz.assessment.model.AnswerDetail
import com.emiratz.assessment.model.Choice
import com.emiratz.assessment.model.Question
import com.emiratz.assessment.model.QuestionRequest
import com.emiratz.assessment.model.QuestionResponse
import com.emiratz.assessment.model.QuestionResultResponse
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
import kotlin.coroutines.CoroutineContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionResultFragment(val listQuestion : List<QuestionResultResponse?>?) : Fragment(),
    CoroutineScope {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var rvQuestionResult: RecyclerView
    private lateinit var questionResultAdapter : QuestionResultAdapter
    private lateinit var job: Job

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
        return inflater.inflate(R.layout.fragment_question_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvQuestionResult = view.findViewById(R.id.rvQuestionResult)

        getAllQuestion()
    }

    fun getAllQuestion() {
        questionResultAdapter = QuestionResultAdapter(listQuestion, requireActivity())
        rvQuestionResult.layoutManager = LinearLayoutManager(context)
        rvQuestionResult.adapter = questionResultAdapter
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