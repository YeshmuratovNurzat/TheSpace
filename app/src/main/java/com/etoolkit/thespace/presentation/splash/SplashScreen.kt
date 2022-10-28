package com.etoolkit.thespace.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.etoolkit.thespace.R
import com.etoolkit.thespace.presentation.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreen : AppCompatActivity() {

    private lateinit var lottieAnimationView: LottieAnimationView
    private lateinit var appName : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        lottieAnimationView = findViewById(R.id.animationView)
        appName = findViewById(R.id.app_name)

        //appName.animate().translationY(-1400f).setDuration(2700).startDelay = 0
        //lottieAnimationView.animate().translationX(3000f).setDuration(1700).startDelay = 2900

        supportActionBar?.hide()

        CoroutineScope(Dispatchers.Main).launch {
            delay(4000)
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }
    }
}