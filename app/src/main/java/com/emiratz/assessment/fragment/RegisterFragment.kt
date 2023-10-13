package com.emiratz.assessment.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.emiratz.assessment.R
import com.emiratz.assessment.model.LoginResponse
import com.emiratz.assessment.model.RegisterRequest
import com.emiratz.assessment.model.RegisterResponse
import com.emiratz.assessment.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var btnRegister: Button
    lateinit var txtNama: EditText
    lateinit var txtEmail: EditText
    lateinit var txtPassword: EditText

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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnRegister = view.findViewById(R.id.btnRegister)
        txtNama = view.findViewById(R.id.editNama)
        txtEmail = view.findViewById(R.id.editEmail)
        txtPassword = view.findViewById(R.id.editPassword)

        btnRegister.setOnClickListener(View.OnClickListener {
            register(
                RegisterRequest(
                    txtNama.text.toString(),
                    txtEmail.text.toString(),
                    txtPassword.text.toString(),
                )
            )
        })
    }

    fun register(data: RegisterRequest) {
        val client = ApiConfig.getApiService()
            .registerData(
                RegisterRequest(data.email.toString(), data.username.toString(), data.password.toString(), data.nama.toString()
                )
            )

        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                Log.i("LOGIN", response.toString())
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    Log.i("LOGIN", "onSuccess: ${responseBody.message}")
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.frmFragmentRoot, LoginFragment())
                        .commit()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
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
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}