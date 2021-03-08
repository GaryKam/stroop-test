package io.github.garykam.strooptest.viewmodel

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.garykam.strooptest.model.Word

class GameViewModel : ViewModel() {
    val words: Array<Word>
    val timeRemaining: LiveData<Int>
        get() = _timeRemaining
    private val _timeRemaining: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    init {
        val possibleWords = setOf("Red", "Green", "Blue")
        val possibleColors = setOf(Color.RED, Color.GREEN, Color.BLUE)
        val temp = mutableListOf<Word>()
        for (i in 0 until 60) {
            temp.add(Word(possibleWords.random(), possibleColors.random()))
        }
        words = temp.toTypedArray()
    }

    fun decrementTime() {
        _timeRemaining.value = _timeRemaining.value?.minus(1)
    }

    fun resetTime() {
        _timeRemaining.value = 60
    }

    fun calculateScore(): Int {
        return words.filter(Word::selected).filter(Word::matchesColor).size
    }
}