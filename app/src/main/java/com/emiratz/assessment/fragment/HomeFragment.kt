package com.emiratz.assessment.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.emiratz.assessment.R
import com.emiratz.assessment.adapter.AssessmentAdapter
import com.emiratz.assessment.adapter.AssessmentResultAdapter
import com.emiratz.assessment.model.AssessmentDetailResponse
import com.emiratz.assessment.model.AssessmentResultResponse
import com.emiratz.assessment.model.ResponseGetAllData
import com.emiratz.assessment.network.ApiConfig
import com.emiratz.assessment.util.DataStoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
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
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), CoroutineScope {
    private lateinit var dataStoreManager: DataStoreManager
//    lateinit var tokenTextView: TextView
//    lateinit var userIdTextView: TextView
    lateinit var logoutButton: Button
    lateinit var rvAssessment: RecyclerView
    private lateinit var assessmentAdapter : AssessmentAdapter
    lateinit var rvAssessmentResult: RecyclerView
    private lateinit var assessmentResultAdapter : AssessmentResultAdapter
    private lateinit var job: Job

    lateinit var swipeRefreshLayout : SwipeRefreshLayout


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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataStoreManager = DataStoreManager(requireContext())
        logoutButton = view.findViewById(R.id.btnLogout)
//        tokenTextView = view.findViewById(R.id.txtToken)
//        userIdTextView = view.findViewById(R.id.txtUserId)
        rvAssessment = view.findViewById(R.id.rvAssessment)
        rvAssessmentResult = view.findViewById(R.id.rvAssessmentResult)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)


//        launch {
//            combine(dataStoreManager.getToken, dataStoreManager.getUserId) { token, userId ->
//                tokenTextView.text = "Token : $token!"
//                userIdTextView.text = "UserId : $userId!"
//            }.collect()
//        }

        // Panggil getAllAssessment dengan token dan userId yang diperoleh dari dataStore
        launch {
            val token = dataStoreManager.getTokenValue()
            val userId = dataStoreManager.getUserIdValue()
            getAllAssessment(token, userId)
            getAllAssessmentResult(token, userId)

            swipeRefreshLayout.setOnRefreshListener {
                getAllAssessment(token, userId)
                getAllAssessmentResult(token, userId)
            }
        }

        logoutButton.setOnClickListener {
            launch {
                clearDataStore()
                val loginFragment = LoginFragment()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frmFragmentRoot, loginFragment)
                    .commit()
            }
        }
    }

    suspend fun clearDataStore() {
        dataStoreManager.clearToken()
        dataStoreManager.clearUserId()
    }

    fun getAllAssessment(token:String, userId:Int){
        val client = ApiConfig.getApiService().getAllAssessment("Bearer $token", userId)

        launch{
            client.enqueue(object : Callback<ResponseGetAllData> {
                override fun onResponse(
                    call: Call<ResponseGetAllData>,
                    response: Response<ResponseGetAllData>
                ) {
                    Log.i("ASSESSMENT", response.toString())
                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null) {
//                        val unfinishedAssessments = responseBody.data?.filter { assessment ->
//                            val hasUserCompleted = assessment?.participants?.any { participant ->
//                                participant?.id == userId && participant.results?.isNotEmpty() ?:true
//                            } == false
//
//                            !hasUserCompleted
//                        }
//                        Log.i("ASSESSMENT", unfinishedAssessments.orEmpty().toString())
                        assessmentAdapter = AssessmentAdapter(responseBody.data!!, requireActivity())
                        rvAssessment.layoutManager = LinearLayoutManager(context)
                        rvAssessment.adapter = assessmentAdapter
                    }
                    swipeRefreshLayout.isRefreshing = false
                }

                override fun onFailure(call: Call<ResponseGetAllData>, t: Throwable) {
                    Log.e("INFO", "onFailure: ${t.message.toString()}")
                    swipeRefreshLayout.isRefreshing = false
                }
            })
        }
    }

    fun getAllAssessmentResult(token:String, userId:Int){
        val client = ApiConfig.getApiService().getAllAssessmentResult("Bearer $token", userId)

        launch{
            client.enqueue(object : Callback<AssessmentResultResponse> {
                override fun onResponse(
                    call: Call<AssessmentResultResponse>,
                    response: Response<AssessmentResultResponse>
                ) {
                    Log.i("ASSESSMENT", response.toString())
                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null) {
                        assessmentResultAdapter = AssessmentResultAdapter(responseBody.data!!, userId, requireActivity())
                        rvAssessmentResult.layoutManager = LinearLayoutManager(context)
                        rvAssessmentResult.adapter = assessmentResultAdapter
                    }
                }

                override fun onFailure(call: Call<AssessmentResultResponse>, t: Throwable) {
                    Log.e("INFO", "onFailure: ${t.message.toString()}")
                }
            })
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}