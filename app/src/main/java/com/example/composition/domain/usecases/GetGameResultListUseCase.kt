package com.example.composition.domain.usecases

import androidx.lifecycle.LiveData
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.repository.GameRepository

class GetGameResultListUseCase(private val gameRepository: GameRepository) {

    fun getGameResult(): LiveData<List<GameResult>> {
        return gameRepository.getGameResultList()
    }

}