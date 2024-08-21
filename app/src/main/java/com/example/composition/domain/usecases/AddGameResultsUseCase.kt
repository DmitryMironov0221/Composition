package com.example.composition.domain.usecases

import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.repository.GameRepository

class AddGameResultsUseCase (private val gameRepository: GameRepository){

    suspend fun addGameResult(gameResult: GameResult){
        gameRepository.addGameResult(gameResult)
    }

}