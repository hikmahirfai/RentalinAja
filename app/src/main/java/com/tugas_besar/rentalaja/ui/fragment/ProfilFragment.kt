package com.tugas_besar.rentalaja.ui.fragment

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.tugas_besar.rentalaja.R
import com.tugas_besar.rentalaja.databinding.FragmentProfilBinding
import com.tugas_besar.rentalaja.ui.activity.EditDokumen
import com.tugas_besar.rentalaja.ui.activity.EditProfil
import com.tugas_besar.rentalaja.ui.activity.Login
import com.tugas_besar.rentalaja.ui.activity.MainActivity
import com.tugas_besar.rentalaja.utils.AuthUtil
import kotlinx.android.synthetic.main.activity_detailmobil.*
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.android.synthetic.main.item_mobil.view.*
import java.io.File


class ProfilFragment : Fragment(R.layout.fragment_profil) {
    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!
    lateinit var filePath: Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUser = AuthUtil.firebaseAuthInstance.currentUser

        val email = currentUser.email
        val uid = currentUser.uid

        binding.tvEmail.text = email

        edit_profil.setOnClickListener {
            val moveTo = Intent(activity, EditProfil::class.java)
            startActivity(moveTo)
        }
        kelengkapan.setOnClickListener {
            val moveTo = Intent(activity, EditDokumen::class.java)
            startActivity(moveTo)
        }

        tv_logout.setOnClickListener {
            AuthUtil.firebaseAuthInstance.signOut()
            val currentUser = AuthUtil.firebaseAuthInstance.currentUser
            if (currentUser == null) {
                val intent = Intent(activity, Login::class.java)
                startActivity(intent)

            }
        }

        pilihgambar.setOnClickListener {
            launchGallery()
        }

        val imageName = FirebaseAuth.getInstance().currentUser!!.uid
        val storageRef = FirebaseStorage.getInstance().reference.child("imageUser/imagePhotoProfile/${imageName}.jpg")
        val localfile = File.createTempFile("tempImage","jpg")
        storageRef.getFile(localfile).addOnSuccessListener{
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            binding.gambarMobil.setImageBitmap(bitmap)
        }
    }

    private fun launchGallery() {
        val i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i, "Select Picture"), 111)

    }

    private fun uploadFile() {
        val currentuser = FirebaseAuth.getInstance().currentUser!!.uid
        if (filePath != null) {
            var pd = ProgressDialog(requireContext())
            pd.setTitle("Uploading")
            pd.show()
            //           val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            var imageRef =
                FirebaseStorage.getInstance().reference.child("imageUser/"+"imagePhotoProfile/" + currentuser+".jpg")
            imageRef.putFile(filePath)
                .addOnSuccessListener { p0 ->
                    pd.dismiss()
                    Toast.makeText(context, "data tersimpan", Toast.LENGTH_LONG).show()
                    val moveTo = Intent(activity, MainActivity::class.java)
                    startActivity(moveTo)
                }
                .addOnFailureListener { p0 ->
                    pd.dismiss()
                    Toast.makeText(context, p0.message, Toast.LENGTH_LONG).show()
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
            uploadFile()

        }

    }
    override fun onDestroy() {
        // Consider not storing the binding instance in a field, if not needed.
        _binding = null
        super.onDestroy()
    }

}