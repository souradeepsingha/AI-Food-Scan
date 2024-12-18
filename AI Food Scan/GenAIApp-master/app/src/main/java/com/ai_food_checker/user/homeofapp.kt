package com.ai_food_checker.user

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ai_food_checker.user.databinding.ActivityHomeofappBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class homeofapp : AppCompatActivity() {

    private lateinit var binding: ActivityHomeofappBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.parseColor("#000000")
        //icon color -> white
        //icon color -> white
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS)
        }

        binding = ActivityHomeofappBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set the initial fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, homemain())
                .commit()
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment = homemain()
            when (item.itemId) {
                R.id.home -> selectedFragment = homemain()
                R.id.chat2 -> selectedFragment = Chatp()
                R.id.chat -> selectedFragment = settingfragment()
                // Uncomment and modify the following block if you want to add sharing functionality
                // R.id.share -> {
                //     val appLink = "This is Chat with character App where you can chat with ai characters. Download the App. This is the Link: https://play.google.com/store/apps/details?id=com.ChatwithCharactersAI.user"
                //     val shareIntent = Intent(Intent.ACTION_SEND)
                //     shareIntent.type = "text/plain"
                //     shareIntent.putExtra(Intent.EXTRA_TEXT, appLink)
                //     startActivity(Intent.createChooser(shareIntent, "Share via"))
                // }
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, selectedFragment)
                .commit()
            true
        }
    }
}
