package com.tugas_besar.rentalaja.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tugas_besar.rentalaja.R
import kotlinx.android.synthetic.main.activity_selesai.*

class Selesai : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selesai)

        btnSelesai.setOnClickListener{
            val moveToSewa = Intent(this, MainActivity::class.java)
            startActivity(moveToSewa)
        }
    }
}