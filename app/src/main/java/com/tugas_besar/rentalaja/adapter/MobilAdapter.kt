package com.tugas_besar.rentalaja.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tugas_besar.rentalaja.R
import com.tugas_besar.rentalaja.ui.activity.DetailMobil
import com.tugas_besar.rentalaja.databinding.ItemMobilBinding
import com.tugas_besar.rentalaja.model.Mobil
import com.tugas_besar.rentalaja.utils.Helper.EXTRA_POSITION
import com.tugas_besar.rentalaja.utils.Helper.EXTRA_QUOTE
import com.tugas_besar.rentalaja.utils.Helper.REQUEST_UPDATE
import kotlinx.android.synthetic.main.item_mobil.view.*

class MobilAdapter(private val activity: Activity): RecyclerView.Adapter<MobilAdapter.MainViewHolder>() {

    private var dataList = mutableListOf<Mobil>()

    fun setListData(data: MutableList<Mobil>){
        dataList =data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.item_mobil, parent, false)
        return MainViewHolder(view)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val mobil = dataList[position]
        holder.bindView(mobil,position)
    }

    override fun getItemCount(): Int {
        return  if(dataList.size>0){
            dataList.size
        }else{
            0
        }
    }
    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val binding = ItemMobilBinding.bind(itemView)

        fun bindView(mobil: Mobil, position: Int){
            Glide.with(activity).load(mobil.gambar).into(itemView.gambar_mobil)
            itemView.tv_merek.text = mobil.merekMobil
            itemView.tv_nama_mobil.text= mobil.namaMobil
            itemView.tv_harga_sewa.text=mobil.hargaSewa

            binding.cvListMobil.setOnClickListener{
                val intent = Intent(activity, DetailMobil::class.java )
                intent.putExtra(EXTRA_POSITION, position)
                intent.putExtra(EXTRA_QUOTE, mobil)
                activity.startActivityForResult(intent, REQUEST_UPDATE)

            }
        }
    }


}
