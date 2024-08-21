package com.example.composition.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.composition.databinding.FragmentGameResultItemDetailedBinding

class GameResultItemDetailed : Fragment() {
    private var _binding: FragmentGameResultItemDetailedBinding? = null
    private val binding: FragmentGameResultItemDetailedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameResultItemDetailedBinding == null")

    private val args by navArgs<GameResultItemDetailedArgs>()
    private lateinit var viewModel: GameResultsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameResultItemDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[GameResultsViewModel::class.java]

        val gameResultId = args.gameResultId
        viewModel.getGameResult(gameResultId)
        viewModel.gameResult.observe(viewLifecycleOwner) { gameResult ->
            binding.gameResult = gameResult // Устанавливаем gameResult в binding
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}