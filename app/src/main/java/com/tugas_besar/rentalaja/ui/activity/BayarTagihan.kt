package com.tugas_besar.rentalaja.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tugas_besar.rentalaja.R
import kotlinx.android.synthetic.main.activity_bayartagihan.*
import kotlinx.android.synthetic.main.activity_bayartagihan.logo_rental
import kotlinx.android.synthetic.main.activity_sewamobil.*

class BayarTagihan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bayartagihan)

        btnLanjut.setOnClickListener{
            val moveToSewa = Intent(this, Selesai::class.java)
            startActivity(moveToSewa)
        }
        logo_rental.setOnClickListener {
            onBackPressed()
        }
    }

}