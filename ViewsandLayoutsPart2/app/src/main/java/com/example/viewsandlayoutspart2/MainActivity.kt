package com.example.viewsandlayoutspart2

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.viewsandlayoutspart2.databinding.ActivityMainNavBinding
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.activityMainLayout.toolbarLayout.toolbar)
        binding.navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.activityMainLayout.toolbarLayout.toolbar, R.string.open_nav_drawer, R.string.close_nav_drawer
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        when(item.itemId) {
            R.id.viewpager_menu_item -> openActivity(ViewPagerActivity::class)
            R.id.webview_menu_item -> openActivity(WebViewActivity::class)
            R.id.recyclerview_menu_item -> openActivity(RecyclerViewActivity::class)
        }
        return true
    }

    private fun <T : AppCompatActivity> openActivity(activity: KClass<T>) {
        startActivity(Intent(this, activity.java))
    }
}