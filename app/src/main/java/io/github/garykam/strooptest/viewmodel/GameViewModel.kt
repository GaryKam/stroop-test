package io.github.garykam.strooptest.viewmodel

import android.graphics.Color
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    val selected = mutableSetOf<Int>()
    val words
        get() = _words
    private val _words: Array<Pair<String, Int>>

    init {
        val possibleWords = setOf("Red", "Green", "Blue")
        val possibleColors = setOf(Color.RED, Color.GREEN, Color.BLUE)
        val words = mutableListOf<Pair<String, Int>>()
        for (i in 0 until 60) {
            words.add(Pair(possibleWords.random(), possibleColors.random()))
        }
        _words = words.toTypedArray()
    }
}