package com.tugas_besar.rentalaja.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.tugas_besar.rentalaja.R
import com.tugas_besar.rentalaja.databinding.ActivitySewamobilBinding
import com.tugas_besar.rentalaja.utils.AuthUtil
import com.tugas_besar.rentalaja.utils.Helper
import kotlinx.android.synthetic.main.activity_sewamobil.*

class SewaMobil : AppCompatActivity(), View.OnClickListener {

    lateinit var firestore: FirebaseFirestore
    private lateinit var binding: ActivitySewamobilBinding
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySewamobilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firestore = Firebase.firestore
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        ///
        val currentUser = AuthUtil.firebaseAuthInstance.currentUser
        if (currentUser == null) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
        //
        binding.btnCheckout.setOnClickListener(this)
        logo_rental.setOnClickListener {
            onBackPressed()
        }
        //

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnCheckout -> {
                sewaMobil(
                    binding.inputNama.text.toString(),
                    binding.inputDurasi.text.toString()
                )

            }
        }
    }

     fun sewaMobil(nama: String, durasiSewa: String) {
         val currentuser = FirebaseAuth.getInstance().currentUser!!.uid
         val totalPembayaran = durasiSewa.toInt()*200000
         val db = FirebaseFirestore.getInstance()
         val dataSewa: MutableMap<String, Any> = HashMap()
         dataSewa["nama"] = nama
         dataSewa["durasiSewa"] = durasiSewa.toString()
         dataSewa["statusSewa"] = "Menunggu"
         dataSewa["totalPembayaran"] =totalPembayaran.toString()

         db.collection("detailSewa").document(currentuser)
             .set(dataSewa)
             .addOnSuccessListener { documentReference ->
                 setResult(Helper.RESULT_ADD, intent)
                 val moveToSewa = Intent(this, BayarTagihan::class.java)
                 startActivity(moveToSewa)
             }
             .addOnFailureListener { e ->
                 Toast.makeText(this, "Error adding document", Toast.LENGTH_SHORT).show()
             }
     }
    ///
}