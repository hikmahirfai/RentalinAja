package com.tugas_besar.rentalaja.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.tugas_besar.rentalaja.R
import com.tugas_besar.rentalaja.adapter.MobilAdapter
import com.tugas_besar.rentalaja.viewmodel.MobilViewModel
import kotlinx.android.synthetic.main.fragment_beranda.*
import kotlinx.android.synthetic.main.fragment_beranda.view.*
import java.util.*


class BerandaFragment : Fragment() {

    private lateinit var adapter: MobilAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(MobilViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View =inflater.inflate(R.layout.fragment_beranda, container, false)
        adapter = MobilAdapter(requireActivity())
        view.rv_mobil.layoutManager= LinearLayoutManager(requireContext())
        view.rv_mobil.adapter =adapter
        observerData()
        return view
    }
    fun observerData(){
        //shimmer_view_container.startShimmer()
        viewModel.fecthMobilData().observe(requireActivity(), Observer {
            shimmer_view_container.stopShimmer()
            shimmer_view_container.visibility = View.GONE

            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

}
