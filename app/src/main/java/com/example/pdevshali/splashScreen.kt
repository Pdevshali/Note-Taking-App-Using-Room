package com.example.pdevshali

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import java.util.logging.Handler

class splashScreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val Tv = findViewById<TextView>(R.id.tv)
        val Lottie = findViewById<LottieAnimationView>(R.id.lottie)

        Tv.animate().translationY(-700F).setDuration(3500).setStartDelay(0)
        Lottie.animate().translationX(2000F).setDuration(2500).setStartDelay(2800)

        android.os.Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)


    }
}