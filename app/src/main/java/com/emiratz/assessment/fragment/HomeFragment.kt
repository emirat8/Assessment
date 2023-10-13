package com.emiratz.assessment.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emiratz.assessment.R
import com.emiratz.assessment.adapter.AssessmentAdapter
import com.emiratz.assessment.model.AssessmentResponse
import com.emiratz.assessment.network.ApiConfig
import com.emiratz.assessment.util.DataStoreManager
import com.emiratz.assessment.util.DataViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    private lateinit var job: Job
    private lateinit var dataViewModel: DataViewModel // ViewModel untuk menyimpan data
    lateinit var tokenTextView: TextView
    lateinit var userIdTextView: TextView
    lateinit var logoutButton: Button
    lateinit var rvAssessment: RecyclerView

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel() // Hentikan semua pekerjaan ketika fragment dihancurkan
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
        tokenTextView = view.findViewById(R.id.txtToken)
        userIdTextView = view.findViewById(R.id.txtUserId)
        rvAssessment = view.findViewById(R.id.rvAssessment)

        // Tampilkan token dari DataStore
        launch {
            dataStoreManager.getToken
                .collect { token ->
                    withContext(Dispatchers.Main) {
                        tokenTextView.text = "Token : $token!"
                    }
                }
        }

        launch {
            dataStoreManager.getUserId
                .collect { userId ->
                    withContext(Dispatchers.Main) {
                        userIdTextView.text = "UserId : $userId!"
                    }
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

//    fun getAllAssessment(){
//        val client = ApiConfig.getApiService().getAllAssessment()
//
//        client.enqueue(object : Callback<AssessmentResponse> {
//            override fun onResponse(
//                call: Call<AssessmentResponse>,
//                response: Response<AssessmentResponse>
//            ) {
//                val responseBody = response.body()
//                if (response.isSuccessful && responseBody != null) {
//                    assessmentAdapter = AssessmentAdapter(responseBody, {item ->
//                        parentFragmentManager.beginTransaction()
//                            .addToBackStack("add form")
//                            .replace(R.id.frmFragmentRoot, AddCollection.newInstance("update", item, "Update Data"))
//                            .commit()
//                    }, {item ->
//                        deleteCollection(item)
//                    })
//                    recyclerView.layoutManager = LinearLayoutManager(context)
//                    recyclerView.adapter = collectionAdapter
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseGetAllData>, t: Throwable) {
//                showProgressBar(false)
//                Log.e("INFO", "onFailure: ${t.message.toString()}")
//            }
//        })
//    }

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