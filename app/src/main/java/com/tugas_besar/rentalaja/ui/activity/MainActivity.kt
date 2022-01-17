package com.tugas_besar.rentalaja.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tugas_besar.rentalaja.R
import com.tugas_besar.rentalaja.ui.fragment.BerandaFragment
import com.tugas_besar.rentalaja.ui.fragment.DetailsewaFragment
import com.tugas_besar.rentalaja.ui.fragment.ProfilFragment

class MainActivity : AppCompatActivity() {

    private val fragmentBeranda: Fragment = BerandaFragment()
    private val fragmentDetailSewa: Fragment = DetailsewaFragment()
    private var fragmentProfil: Fragment = ProfilFragment()
    private val fm: FragmentManager = supportFragmentManager
    private var active: Fragment = fragmentBeranda

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView
    private var backPressedTime = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottomNav()
    }

    fun setUpBottomNav(){
        fm.beginTransaction().add(R.id.container, fragmentBeranda).show(fragmentBeranda).commit()
        fm.beginTransaction().add(R.id.container, fragmentDetailSewa).hide(fragmentDetailSewa).commit()
        fm.beginTransaction().add(R.id.container, fragmentProfil).hide(fragmentProfil).commit()

        bottomNavigationView = findViewById(R.id.bottom_nav)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when(item.itemId) {
                R.id.beranda ->{
                    callFargment(0, fragmentBeranda)
                }
                R.id.detail_sewa ->{
                    callFargment(1, fragmentDetailSewa)
                }
                R.id.profil ->{
                    callFargment(2, fragmentProfil)
                }
            }

            false
        }
    }

    fun callFargment(int: Int, fragment: Fragment){
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
        }else{
            Toast.makeText(applicationContext,"tekan kembali untuk keluar", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}