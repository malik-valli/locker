package com.example.locker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.databinding.ActivityEndingBinding

class EndingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEndingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEndingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.agreeButton.setOnClickListener {
            val activityMain = Intent(this, MainActivity::class.java)
            startActivity(activityMain)
        }
        binding.disagreeButton.setOnClickListener { finishAffinity() }
    }
}