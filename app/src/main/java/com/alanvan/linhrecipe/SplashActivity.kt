package com.alanvan.linhrecipe

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alanvan.linhrecipe.utilities.startActivitySafe

class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window?.statusBarColor = ContextCompat.getColor(this, R.color.colorBlack)

        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        splashViewModel.tokenLiveData.observe(this, Observer { token ->
            if (token != null) {
                startMainActivity()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        splashViewModel.initialize()
    }

    override fun onPause() {
        super.onPause()
        splashViewModel.cleanUp()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.data = this.intent.data

        if (intent.data != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivitySafe(intent)
        } else {
            startActivitySafe(intent)
        }
    }
}
