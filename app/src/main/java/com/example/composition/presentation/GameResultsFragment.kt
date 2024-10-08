package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.composition.R
import com.example.composition.databinding.FragmentGameResultsBinding
import com.example.composition.domain.repository.GameRepository
import com.example.composition.domain.usecases.GetGameResultListUseCase

/**
 * A simple [Fragment] subclass.
 * Use the [Game_results.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameResultsFragment : Fragment() {
    private lateinit var viewModel: GameResultsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[GameResultsViewModel::class.java]

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_game_results)
        val adapter = GameResultsAdapter()
        recyclerView.adapter = adapter

        viewModel.gameResultList.observe(viewLifecycleOwner) { gameResults ->
            adapter.setGameResults(gameResults)
        }


    }

}