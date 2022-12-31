package com.auxilitos.mis_primeros_auxilitos.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.auxilitos.mis_primeros_auxilitos.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splahScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splahScreen.setKeepOnScreenCondition{ true }
        val i = Intent(this, Login::class.java)
        startActivity(i)
        finish()

    }
}