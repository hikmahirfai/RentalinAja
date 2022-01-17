package com.tugas_besar.rentalaja.repositori

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.tugas_besar.rentalaja.model.Mobil
import com.tugas_besar.rentalaja.model.User

class Repo  {

    fun getMobilData():LiveData<MutableList<Mobil>>{
        val mutableData = MutableLiveData<MutableList<Mobil>>()
        FirebaseFirestore.getInstance().collection("mobilSewa").get().addOnSuccessListener { result ->
            val listData= mutableListOf<Mobil>()
            for(document in result){
                val gambar:String? = document.getString("gambar")
                val hargaSewa:String? =document.getString("hargaSewa")
                val kapasitasOrang:String? =document.getString("kapasitasOrang")
                val kapasitasTangki:String? =document.getString("kapasitasTangki")
                val merekMobil:String? =document.getString("merekMobil")
                val namaMobil:String? =document.getString("namaMobil")
                val tipeMesin:String? =document.getString("tipeMesin")
                val tipeMobil:String? =document.getString("tipeMobil")
                val dataMobil = Mobil(gambar!!,hargaSewa!!,kapasitasOrang!!,kapasitasTangki!!,merekMobil!!,namaMobil!!,tipeMesin!!,tipeMobil!!)
                listData.add(dataMobil)
            }
            mutableData.value =listData
        }
        return mutableData
    }

    fun getProfilData():LiveData<MutableList<User>>{
        val mutableData = MutableLiveData<MutableList<User>>()
        FirebaseFirestore.getInstance().collection("dataProfil").get().addOnSuccessListener { result ->
            val listData= mutableListOf<User>()
            for(document in result){
                val nama:String? = document.getString("nama")
                val alamat:String? =document.getString("alamat")
                val noHp:String? =document.getString("noHp")
                val uid:String? =document.getString("uid")
                val dataProfile = User(nama!!,alamat!!,noHp!!,uid!!)
                listData.add(dataProfile)
            }
            mutableData.value =listData
        }
        return mutableData
    }

}