package io.github.garykam.strooptest.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import io.github.garykam.strooptest.R

class WordsAdapter(
    private val wordListener: WordListener,
    private val words: Array<Pair<String, Int>>
) : RecyclerView.Adapter<WordsAdapter.WordHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        return WordHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.button_colored_word, parent, false)
        )
    }

    override fun getItemCount() = words.size

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        val wordData = words[position]
        val wordButton = holder.itemView as Button
        wordButton.text = wordData.first
        wordButton.setTextColor(wordData.second)
    }

    inner class WordHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            wordListener.onClick(view, adapterPosition)
        }
    }

    interface WordListener {
        fun onClick(view: View?, position: Int)
    }
}