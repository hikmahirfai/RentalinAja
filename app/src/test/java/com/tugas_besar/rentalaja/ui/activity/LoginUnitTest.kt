package com.tugas_besar.rentalaja.ui.activity

import org.junit.Assert.*
import org.junit.Test

class LoginUnitTest{
    private lateinit var signinakun :Login
    private val emailEmpty = "harap masukan email"

    @Test
    fun signInAkun(){

        signinakun = Login()
        val signin = signinakun.validateForm()
        assertEquals(emailEmpty , signin)
    }
}