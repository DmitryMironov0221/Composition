package com.example.composition.domain.repository

import androidx.lifecycle.LiveData
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOption: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings

    fun getGameResultList(): LiveData<List<GameResult>>
    fun getGameResult(id: Int): GameResult

    suspend fun addGameResult(gameResult: GameResult)
}