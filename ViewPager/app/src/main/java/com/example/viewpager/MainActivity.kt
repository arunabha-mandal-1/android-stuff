package com.example.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = listOf(
            R.drawable.profile1,
            R.drawable.profile2,
            R.drawable.profile3,
            R.drawable.profile4,
        )
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = ViewPagerAdapter(images)
        viewPager.adapter = adapter

        // optional
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL


//        viewPager.beginFakeDrag()
//        viewPager.fakeDragBy(-8f)
//        viewPager.endFakeDrag()
    }
}