package com.tugas_besar.rentalaja.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugas_besar.rentalaja.model.Mobil
import com.tugas_besar.rentalaja.repositori.Repo

class MobilViewModel: ViewModel() {
    private val repo = Repo()
    fun fecthMobilData():LiveData<MutableList<Mobil>>{
        val mutableData = MutableLiveData<MutableList<Mobil>>()
        repo.getMobilData().observeForever{mobilList->
            mutableData.value = mobilList
        }
        return mutableData
    }
}