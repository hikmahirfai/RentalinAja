package com.tugas_besar.rentalaja.ui.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.tugas_besar.rentalaja.R
import com.tugas_besar.rentalaja.databinding.ActivityEditProfilBinding
import com.tugas_besar.rentalaja.model.Mobil
import com.tugas_besar.rentalaja.model.User
import com.tugas_besar.rentalaja.utils.AuthUtil
import com.tugas_besar.rentalaja.utils.Helper.RESULT_ADD
import kotlinx.android.synthetic.main.activity_edit_profil.*
import kotlinx.android.synthetic.main.activity_edit_profil.logo_rental
import kotlinx.android.synthetic.main.activity_sewamobil.*
import java.util.*
import kotlin.collections.HashMap

class EditProfil : AppCompatActivity(), View.OnClickListener {
    lateinit var firestore: FirebaseFirestore
    private lateinit var binding: ActivityEditProfilBinding
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfilBinding.inflate(layoutInflater)
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
        binding.btnSimpan.setOnClickListener(this)

        logo_rental.setOnClickListener {
            onBackPressed()
        }

    }

    ///
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_simpan -> {
                editUser(
                    binding.inputNama.text.toString(),
                    binding.inputNohp.text.toString(),
                    binding.inputAlamat.text.toString()
                )

            }
        }
    }

    //
    fun editUser(nama: String, noHp: String, alamat: String) {
        val currentuser = FirebaseAuth.getInstance().currentUser!!.uid

        val db = FirebaseFirestore.getInstance()
        val dataprofil: MutableMap<String, Any> = HashMap()
        dataprofil["uid"] = currentuser
        dataprofil["nama"] = nama
        dataprofil["noHp"] = noHp
        dataprofil["alamat"] = alamat

        db.collection("dataProfil").document(currentuser)
            .set(dataprofil)
            .addOnSuccessListener { documentReference ->
                setResult(RESULT_ADD, intent)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error adding document", Toast.LENGTH_SHORT).show()
            }
    }
    ///




}