package io.github.garykam.strooptest.model

import android.graphics.Color

data class Word(
    val text: String,
    val textColor: Int,
    var selected: Boolean = false
) {
    fun matchesColor(): Boolean {
        return when(text) {
            "Red" -> textColor == Color.RED
            "Green" -> textColor == Color.GREEN
            "Blue" -> textColor == Color.BLUE
            else -> false
        }
    }
}