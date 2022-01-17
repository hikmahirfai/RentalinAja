package com.tugas_besar.rentalaja.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.tugas_besar.rentalaja.R
import com.tugas_besar.rentalaja.databinding.ActivityDetailmobilBinding
import com.tugas_besar.rentalaja.model.Mobil
import com.tugas_besar.rentalaja.utils.Helper.EXTRA_QUOTE
import kotlinx.android.synthetic.main.activity_detailmobil.*

class DetailMobil : AppCompatActivity() {
    private var detailMobil: Mobil? = null
    private lateinit var binding: ActivityDetailmobilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailmobilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailMobil = intent.getParcelableExtra(EXTRA_QUOTE)

        detailMobil?.let {
            binding.tvItemTitle.setText(it.merekMobil)
            binding.tvItemCategory.setText(it.namaMobil)
            binding.tvItemDescription.setText(it.hargaSewa)
            binding.tvLiter.setText(it.kapasitasTangki)
            binding.tvOrang.setText(it.kapasitasOrang)
            binding.tvTipeMesin.setText(it.tipeMesin)
            binding.tvTipeMobil.setText(it.tipeMobil)
            Glide.with(this).load(it.gambar).into(binding.gambarMobil)


        }!!
        btnSewa.setOnClickListener{
            val moveToSewa = Intent(this, SewaMobil::class.java)
            startActivity(moveToSewa)
        }
        logo_rental.setOnClickListener {
            onBackPressed()
        }
    }
}