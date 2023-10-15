package com.emiratz.assessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.emiratz.assessment.fragment.HomeFragment
import com.emiratz.assessment.fragment.LoginFragment
import com.emiratz.assessment.fragment.RegisterFragment
import com.emiratz.assessment.util.DataStoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Inisialisasi DataStoreManager
        val dataStoreManager = DataStoreManager(applicationContext)

        // Periksa apakah username ada dalam DataStore
        CoroutineScope(Dispatchers.Main).launch {
            val token = dataStoreManager.getTokenValue()
            val fragment: Fragment = if (token.isNotEmpty()) {
                // Jika username ditemukan, tampilkan fragment home
                HomeFragment()
            } else {
                // Jika username tidak ditemukan, tampilkan fragment login
                LoginFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.frmFragmentRoot, fragment)
                .commit()
        }
    }
}