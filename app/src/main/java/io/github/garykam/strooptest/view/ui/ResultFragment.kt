package io.github.garykam.strooptest.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import io.github.garykam.strooptest.R

class ResultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val correct = arguments?.getInt("correct") ?: 0
        val incorrect = arguments?.getInt("incorrect") ?: 0

        view.findViewById<TextView>(R.id.text_score).text = (correct - incorrect).toString()
        view.findViewById<TextView>(R.id.text_correct).text = correct.toString()
        view.findViewById<TextView>(R.id.text_incorrect).text = incorrect.toString()
    }
}