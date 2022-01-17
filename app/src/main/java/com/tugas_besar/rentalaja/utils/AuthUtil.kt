package com.tugas_besar.rentalaja.utils

import com.google.firebase.auth.FirebaseAuth

object AuthUtil {

    val firebaseAuthInstance: FirebaseAuth by lazy {
        println("firebaseAuthInstance.:")
        FirebaseAuth.getInstance()
    }

    fun getAuthId(): String {
        return firebaseAuthInstance.currentUser!!.uid
    }


}