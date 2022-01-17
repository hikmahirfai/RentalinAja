package com.tugas_besar.rentalaja.ui.activity.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.tugas_besar.rentalaja.R

class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        Handler(mainLooper).postDelayed({
            val intent = Intent(this, Onboarding::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}