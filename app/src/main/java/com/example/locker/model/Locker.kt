package com.example.locker.model

import android.util.Log
import android.widget.Button

object Locker {
    private val values = IntArray(4)

    private lateinit var rightValues: IntArray

    var listeners = mutableListOf<Button>()

    val rightValuesText: String
        get() {
            var text = ""
            rightValues.forEach { text += it.toString() }
            return text
        }

    var signal = {}

    fun generateRightValues() {
        rightValues = IntArray(4) { (0..9).random() }
        Log.d(null, rightValues.asList().toString())
    }

    fun scrollCell(position: Int) {
        values[position] = ++values[position] % 10
        listeners[position].text = values[position].toString()

        if (values[position] == rightValues[position])
            signal()
    }

    fun checkCorrectness() = values.contentEquals(rightValues)
}