package com.emiratz.assessment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emiratz.assessment.R
import com.emiratz.assessment.adapter.QuestionAdapter
import com.emiratz.assessment.model.AssessmentDummy
import com.emiratz.assessment.model.QuestionResponse

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionFragment(val listQuestion : List<QuestionResponse?>?) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var rvQuestion: RecyclerView
    private lateinit var questionAdapter : QuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvQuestion = view.findViewById(R.id.rvQuestion)
        getAllQuestion()

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