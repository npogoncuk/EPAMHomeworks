package com.example.viewsandlayoutspart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.viewsandlayoutspart2.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarLayout.toolbar.apply {
            title = "ViewPager Task"
        })
        binding.viewPager.adapter = ViewPagerAdapter(this)
    }

    override fun onBackPressed() {
        if (binding.viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding.viewPager.currentItem -= 1
        }
    }


    class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa){

        private val fragments = (1..3).map { ViewPagerFragment.newInstance(it) }.toMutableList()
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]


        /**
         * Add fragment4
         *
         * @return the number of fragments before adding 4-th fragment
         */
        fun addFragment4(): Int {
            fragments += ViewPagerFragment.newInstance(4)
            notifyItemInserted(3)
            return fragments.size - 1
        }
    }

}