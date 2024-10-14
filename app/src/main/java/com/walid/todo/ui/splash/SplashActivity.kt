package com.walid.todo.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.walid.todo.MainActivity
import com.walid.todo.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
           openMainActivity()
        },1000)
    }

    private fun openMainActivity() {
        val i = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}