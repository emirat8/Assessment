package com.emiratz.assessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emiratz.assessment.fragment.LoginFragment
import com.emiratz.assessment.fragment.RegisterFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frmFragmentRoot, LoginFragment.newInstance("", ""))
                .commit()
        }
    }
}