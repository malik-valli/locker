package com.example.locker.model

import android.util.Log
import android.widget.Button

// TODO make a linking to the activity buttons
object Locker {
    private val _values = IntArray(4)
    val values: IntArray
        get() = _values.copyOf()

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
        _values[position] = ++_values[position] % 10
        listeners[position].text = _values[position].toString()

        if (_values[position] == rightValues[position])
            signal()
    }

    fun checkCorrectness() = _values.contentEquals(rightValues)
}