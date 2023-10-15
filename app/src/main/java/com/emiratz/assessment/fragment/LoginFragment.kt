package com.emiratz.assessment.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.emiratz.assessment.R
import com.emiratz.assessment.model.LoginRequest
import com.emiratz.assessment.model.LoginResponse
import com.emiratz.assessment.network.ApiConfig
import com.emiratz.assessment.util.DataStoreManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(), CoroutineScope {
    private lateinit var dataStoreManager: DataStoreManager
    lateinit var loginButton: Button
    lateinit var navigateRegisterButton: TextView
    lateinit var usernameEditText: EditText
    lateinit var passwordEditText: EditText
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
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataStoreManager = DataStoreManager(requireContext())
        loginButton = view.findViewById(R.id.btnLogin)
        navigateRegisterButton = view.findViewById(R.id.btnNavigateRegister)
        usernameEditText = view.findViewById(R.id.editUsername)
        passwordEditText = view.findViewById(R.id.editPassword)

        navigateRegisterButton.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.frmFragmentRoot, RegisterFragment())
                .commit()
        }

        loginButton.setOnClickListener {
            login(
                LoginRequest(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString(),
                )
            )
        }
    }

    fun login(data: LoginRequest) {
        val client = ApiConfig.getApiService()
            .loginData(
                LoginRequest(
                    data.username.toString(),
                    data.password.toString()
                )
            )

        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                Log.i("LOGIN", response.toString())
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    launch {
                        Log.i("DATASTORE", responseBody.data!!.token.toString())
                        dataStoreManager.saveToken(responseBody.data!!.token.toString())
                        Log.i("DATASTORE", responseBody.data!!.id!!.toString())
                        dataStoreManager.saveUserId(responseBody.data!!.id!!)

                        // Ganti fragment ke HomeFragment setelah semua tugas selesai
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.frmFragmentRoot, HomeFragment())
                            .commit()
                    }

                    Log.i("LOGIN", "onSuccess: ${responseBody.data}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("LOGIN", "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}