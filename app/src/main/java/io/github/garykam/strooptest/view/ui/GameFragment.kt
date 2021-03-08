package io.github.garykam.strooptest.view.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.github.garykam.strooptest.R
import io.github.garykam.strooptest.view.adapter.WordsAdapter
import io.github.garykam.strooptest.viewmodel.GameViewModel

class GameFragment : Fragment(), WordsAdapter.WordListener {
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val wordsRecycler: RecyclerView = view.findViewById(R.id.recycler_words)
        wordsRecycler.adapter = WordsAdapter(this, viewModel.words)

        viewModel.resetTime()
        viewModel.timeRemaining.observe(viewLifecycleOwner, Observer<Int> {
            val countdownBar: ProgressBar = view.findViewById(R.id.progressBar_countdown)
            countdownBar.setProgress(it, true)

            if (it <= 0) {
                findNavController().navigate(
                    R.id.action_game_to_result, bundleOf("score" to viewModel.calculateScore())
                )
            }
        })

        val time = viewModel.timeRemaining.value?.times(1000L) ?: 60000L
        object: CountDownTimer(time, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                viewModel.decrementTime()
            }

            override fun onFinish() {}
        }.start()
    }

    override fun onClick(view: View?, position: Int) {
        val word = viewModel.words[position]
        word.selected = !word.selected
        if (word.selected) {
            view?.backgroundTintList = ColorStateList.valueOf(Color.LTGRAY)
        } else {
            view?.backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
        }
    }
}