package com.example.hashdashassignment.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.hashdashassignment.R
import com.example.hashdashassignment.fragment.HomeFragment
import com.example.hashdashassignment.fragment.OrdersFragment
import com.example.hashdashassignment.fragment.PointsFragment
import com.example.hashdashassignment.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bnv: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnv = findViewById(R.id.bottomNavigationView)

        bnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment(), 1)
                }

                R.id.points -> {
                    loadFragment(PointsFragment(), 1)
                }

                R.id.orders -> {
                    loadFragment(OrdersFragment(), 1)
                }

                R.id.profile -> {
                    loadFragment(ProfileFragment(), 1)
                }
            }
            true
        }

        // default fragment
        loadFragment(ProfileFragment(), 0)
        bnv.selectedItemId = R.id.profile
    }

    private fun loadFragment(fragment: Fragment, flag: Int){
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        if(flag == 0)
            ft.add(R.id.container, fragment)
        else
            ft.replace(R.id.container, fragment)
        ft.commit()
    }
}