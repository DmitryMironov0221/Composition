package com.example.composition.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.composition.R
import com.example.composition.databinding.FragmentResultItemBinding
import com.example.composition.domain.entity.GameResult
import java.time.format.DateTimeFormatter

class GameResultsAdapter : RecyclerView.Adapter<GameResultsAdapter.GameResultViewHolder>() {
    private var gameResults: List<GameResult> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameResultViewHolder {
        val binding = FragmentResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = GameResultViewHolder(binding)
        binding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val gameResult = gameResults[position]
                val action= GameResultsFragmentDirections.actionGameResultsFragmentToGameResultItemDetailed(gameResult.id)
                binding.root.findNavController().navigate(action) // Вызываем navigate здесь
            }
        }
        return holder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GameResultViewHolder, position: Int) {
        val gameResult = gameResults[position]
        holder.binding.tvPlayNumber.text = "Игра №${gameResult.id}"
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm") // Формат без секунд и сотых
        holder.binding.tvDateTime.text = gameResult.dateTime.format(formatter)

        // Установи картинку для победы/поражения
        val resultImageResId = if (gameResult.winner) R.drawable.smile else R.drawable.sad
        holder.binding.ivResult.setImageResource(resultImageResId)


    }

    override fun getItemCount(): Int {
        return gameResults.size
    }
    class GameResultViewHolder(val binding: FragmentResultItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    fun setGameResults(results: List<GameResult>) {
        this.gameResults = results
        notifyDataSetChanged()
    }



}
