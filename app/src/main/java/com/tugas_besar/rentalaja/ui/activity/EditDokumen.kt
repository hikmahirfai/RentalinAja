package com.tugas_besar.rentalaja.ui.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.tugas_besar.rentalaja.databinding.ActivityEditDokumenBinding
import com.tugas_besar.rentalaja.utils.AuthUtil
import kotlinx.android.synthetic.main.activity_edit_dokumen.*
import kotlinx.android.synthetic.main.activity_sewamobil.*

class EditDokumen : AppCompatActivity() {
    lateinit var firestore: FirebaseFirestore
    private lateinit var binding: ActivityEditDokumenBinding
    lateinit var filePath: Uri
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDokumenBinding.inflate(layoutInflater)
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
        pilih_ktp.setOnClickListener {
            launchGallery()
        }
        pilih_kk.setOnClickListener {
            launchGallery()
        }
        pilih_sim.setOnClickListener {
            launchGallery()
        }
        pilih_stnk.setOnClickListener {
            launchGallery()
        }
        tombol_back.setOnClickListener {
            onBackPressed()
        }

        logo_rental.setOnClickListener {
            onBackPressed()
        }
    }


    private fun launchGallery() {
        val i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i, "Select Picture"), 111)


    }

    private fun uploadKtp() {
        val currentuser = FirebaseAuth.getInstance().currentUser!!.uid
        if (filePath != null) {
            var pd = ProgressDialog(this)
            pd.setTitle("Uploading")
            pd.show()
            //           val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            var imageRef =
                FirebaseStorage.getInstance().reference.child("imageUser/imageDocument_${currentuser.toString()}/" + "ktp_"+currentuser+".jpg")
            imageRef.putFile(filePath)
                .addOnSuccessListener { p0 ->
                    pd.dismiss()
                   // finish()
                }
                .addOnFailureListener { p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext, p0.message, Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener { p0 ->
                    var progress = (100.0 * p0.bytesTransferred) / p0.totalByteCount
                    pd.setMessage("Uploaded ${progress.toInt()}%")

                }
        }
    }

    private fun uploadKk() {
        val currentuser = FirebaseAuth.getInstance().currentUser!!.uid
        if (filePath != null) {
            var pd = ProgressDialog(this)
            pd.setTitle("Uploading")
            pd.show()
            //           val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            var imageRef =
                FirebaseStorage.getInstance().reference.child("imageUser/imageDocument_${currentuser.toString()}/" + "kk_"+currentuser+".jpg")
            imageRef.putFile(filePath)
                .addOnSuccessListener { p0 ->
                    pd.dismiss()
                    // finish()
                }
                .addOnFailureListener { p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext, p0.message, Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener { p0 ->
                    var progress = (100.0 * p0.bytesTransferred) / p0.totalByteCount
                    pd.setMessage("Uploaded ${progress.toInt()}%")

                }
        }
    }
    private fun uploadSim() {
        val currentuser = FirebaseAuth.getInstance().currentUser!!.uid
        if (filePath != null) {
            var pd = ProgressDialog(this)
            pd.setTitle("Uploading")
            pd.show()
            //           val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            var imageRef =
                FirebaseStorage.getInstance().reference.child("imageUser/imageDocument_${currentuser.toString()}/" + "sim_"+currentuser+".jpg")
            imageRef.putFile(filePath)
                .addOnSuccessListener { p0 ->
                    pd.dismiss()
                    // finish()
                }
                .addOnFailureListener { p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext, p0.message, Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener { p0 ->
                    var progress = (100.0 * p0.bytesTransferred) / p0.totalByteCount
                    pd.setMessage("Uploaded ${progress.toInt()}%")

                }
        }
    }
    private fun uploadStnk() {
        val currentuser = FirebaseAuth.getInstance().currentUser!!.uid
        if (filePath != null) {
            var pd = ProgressDialog(this)
            pd.setTitle("Uploading")
            pd.show()
            //           val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            var imageRef =
                FirebaseStorage.getInstance().reference.child("imageUser/imageDocument_${currentuser.toString()}/" + "stnk_"+currentuser+".jpg")
            imageRef.putFile(filePath)
                .addOnSuccessListener { p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext, "data tersimpan", Toast.LENGTH_LONG).show()
                    // finish()
                }
                .addOnFailureListener { p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext, p0.message, Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener { p0 ->
                    var progress = (100.0 * p0.bytesTransferred) / p0.totalByteCount
                    pd.setMessage("Uploaded ${progress.toInt()}%")

                }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == Activity.RESULT_OK && data != null) {
            filePath = data.data!!
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
            uploadKtp()
            uploadKk()
            uploadSim()
            uploadStnk()

        }

    }
}