package com.tugas_besar.rentalaja.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Mobil (
    val gambar:String ?=null,
    val hargaSewa:String ?=null,
    val kapasitasOrang:String ?=null,
    val kapasitasTangki:String ?=null,
    val merekMobil:String ?=null,
    val namaMobil:String ?=null,
    val tipeMesin:String ?=null,
    val tipeMobil:String ?=null
): Parcelable