package com.emiratz.assessment.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.emiratz.assessment.R
import com.emiratz.assessment.model.LoginRequest
import com.emiratz.assessment.model.LoginResponse
import com.emiratz.assessment.network.ApiConfig
import com.emiratz.assessment.util.UserStore
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var btnLogin: Button
    lateinit var txtUsername: EditText
    lateinit var txtPassword: EditText
    private lateinit var userStore: UserStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userStore = UserStore(requireContext())
        CoroutineScope(Dispatchers.Main).launch {
            userStore.getAccessToken.collect { accessToken ->
                Log.i("DATASTORE", accessToken)
                if (accessToken != "") {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.frmFragmentRoot, HomeFragment.newInstance("", ""))
                        .commit()
                }
            }
        }
        btnLogin = view.findViewById(R.id.btnLogin)
        txtUsername = view.findViewById(R.id.editUsername)
        txtPassword = view.findViewById(R.id.editPassword)

        btnLogin.setOnClickListener(View.OnClickListener {
            login(
                LoginRequest(
                    txtUsername.text.toString(),
                    txtPassword.text.toString(),
                )
            )
        })
    }

    suspend fun checkToken(){
        CoroutineScope(Dispatchers.Main).launch {
            userStore.getAccessToken.collect { accessToken ->
                Log.i("DATASTORE", accessToken)
                if (accessToken != "") {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.frmFragmentRoot, HomeFragment.newInstance("", ""))
                        .commit()
                }
            }
        }
    }
    fun login(data: LoginRequest) {
        val client = ApiConfig.getApiService()
            .loginData(
                LoginRequest(
                    data.userName.toString(),
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
                    CoroutineScope(Dispatchers.Main).launch {
                        userStore.saveToken(responseBody.data!!.token.toString())
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.frmFragmentRoot, HomeFragment.newInstance("", ""))
                            .commit()
                    }
                    Log.e("LOGIN", "onSuccess: ${responseBody.data}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("LOGIN", "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun toRequestBody(value: String): RequestBody {
        return value.toRequestBody("text/plain".toMediaTypeOrNull())
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