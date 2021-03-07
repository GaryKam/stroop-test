package io.github.garykam.strooptest.view.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
    }

    override fun onClick(view: View?, position: Int) {
        if (viewModel.selected.contains(position)) {
            viewModel.selected.remove(position)
            view?.setBackgroundColor(Color.TRANSPARENT)
        } else {
            viewModel.selected.add(position)
            view?.setBackgroundColor(Color.LTGRAY)
        }
    }
}