package com.ai_food_checker.user

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.ai_food_checker.user.databinding.ActivitySettingsBinding

class settings : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

binding.privacypolicy.setOnClickListener{
    val intent = Intent(this, Privacypolicy::class.java)

    startActivity(intent)
}

        binding.shareapp.setOnClickListener{
            Toast.makeText(applicationContext, "Comming soon", Toast.LENGTH_SHORT).show()

        }

        binding.rateus.setOnClickListener{
            Toast.makeText(applicationContext, "Comming soon", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_settings)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}