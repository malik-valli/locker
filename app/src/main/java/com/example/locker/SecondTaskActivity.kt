package com.example.locker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.databinding.ActivitySecondTaskBinding
import com.example.locker.model.Locker

class SecondTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondTaskBinding
    private lateinit var submitButton: Button
    private lateinit var textField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        submitButton = binding.submitButton
        textField = binding.rememberedPasswordEditText

        textField.setOnKeyListener { _, _, _ -> handleKeyEvent() }

        submitButton.setOnClickListener {
            val activityEnding = Intent(this, EndingActivity::class.java)
            startActivity(activityEnding)
        }
    }

    private fun changeUIForCorrectness(isCorrect: Boolean) {
        fun changeSubmitButton() {
            submitButton.isEnabled = isCorrect
        }

        changeSubmitButton()
    }

    private fun handleKeyEvent(): Boolean {
        val isCorrect = textField.text.toString() == Locker.rightValuesText.reversed()
        if (isCorrect || submitButton.isEnabled) changeUIForCorrectness(isCorrect)
        Log.d(null, textField.text.toString() + ", " + Locker.rightValuesText)
        return false
    }
}