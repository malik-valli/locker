package com.example.locker

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.locker.databinding.ActivityMainBinding
import com.example.locker.model.Locker

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var submitButton: Button
    private lateinit var labelText: TextView

    private lateinit var lockSound: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        submitButton = binding.submitButton
        labelText = binding.questionText

        lockSound = MediaPlayer.create(this, R.raw.lock_sound)

        Locker.signal = { lockSound.start() }
        Locker.generateRightValues()

        val cells = listOf(binding.cell1, binding.cell2, binding.cell3, binding.cell4)
        cells.forEach {
            Locker.listeners.add(it)
            it.setOnClickListener { view -> scrollCell(cells.indexOf(view)) }
        }

        submitButton.setOnClickListener {
            val activitySecondTask = Intent(this, SecondTaskActivity::class.java)
            startActivity(activitySecondTask)
        }
    }

    private fun scrollCell(position: Int) {
        Locker.scrollCell(position)
        if (Locker.checkCorrectness() || submitButton.isEnabled)
            changeUIForCorrectness(Locker.checkCorrectness())
    }

    private fun changeUIForCorrectness(isCorrect: Boolean) {
        fun changeLabel() {
            labelText.text =
                if (isCorrect) getString(R.string.you_did_it)
                else getString(R.string.can_you_hack_it)
        }

        fun changeEnterButton() {
            submitButton.isEnabled = isCorrect
            submitButton.text =
                if (isCorrect) getString(R.string.submit)
                else getString(R.string.submit_disabled)
        }
        changeLabel()
        changeEnterButton()
    }
}