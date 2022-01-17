package com.tugas_besar.rentalaja.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.tugas_besar.rentalaja.R
import com.tugas_besar.rentalaja.databinding.ActivityLoginBinding
import com.tugas_besar.rentalaja.utils.AuthUtil
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var email:String
    private var backPressedTime = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnMasuk.setOnClickListener(this)
        link_daftar.setOnClickListener {
            val moveToRegister = Intent(this, Register::class.java)
            startActivity(moveToRegister)
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnMasuk -> {
                signIn(
                    binding.inputEmail.text.toString(),
                    binding.inputPassword.text.toString()
                )
                closeKeyBoard()
            }

        }
    }

    fun signIn(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        AuthUtil.firebaseAuthInstance.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext, "Login User Success.",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

     fun validateForm(): Boolean {
        var valid = true
        val email = binding.inputEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            binding.inputEmail.error = "harap masukan email"
            valid = false
        } else {
            binding.inputEmail.error = null
        }
        val password = binding.inputPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.inputPassword.error = "harap masukan password"
            valid = false
        } else {
            binding.inputPassword.error = null
        }
        return valid
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(applicationContext, "tekan kembali untuk keluar", Toast.LENGTH_SHORT)
                .show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}