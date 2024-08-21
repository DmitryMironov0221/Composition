package com.example.composition.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.GameSettings
import java.time.LocalDateTime

class Mapper {
    @RequiresApi(Build.VERSION_CODES.O)
    fun mapDbModelToEntity(gameResultDbModel: GameResultDbModel) = GameResult(
        id = gameResultDbModel.id,
        winner = gameResultDbModel.winner,
        countOfRightAnswers = gameResultDbModel.countOfRightAnswers,
        countOfQuestions = gameResultDbModel.countOfQuestions,
        dateTime = gameResultDbModel.dateTime,
        gameSettings = GameSettings(
            minCountOfRightAnswers = gameResultDbModel.gameSettings.minCountOfRightAnswers,
            minPercentOfRightAnswers = gameResultDbModel.gameSettings.minPercentOfRightAnswers,
            gameTimeInSeconds = gameResultDbModel.gameSettings.gameTimeInSeconds,
            maxSumValue = gameResultDbModel.gameSettings.maxSumValue
        ))

    @RequiresApi(Build.VERSION_CODES.O)
    fun mapEntityToDbModel(gameResult: GameResult) =
        GameResultDbModel(
            id = gameResult.id,
            winner = gameResult.winner,
            countOfRightAnswers = gameResult.countOfRightAnswers,
            countOfQuestions = gameResult.countOfQuestions,
            dateTime = LocalDateTime.now(),
            gameSettings = GameSettingsDbModel(
                gameSettingsId = 0, // Здесь id не важен, так как он генерируется автоматически
                minCountOfRightAnswers = gameResult.gameSettings.minCountOfRightAnswers,
                minPercentOfRightAnswers = gameResult.gameSettings.minPercentOfRightAnswers,
                gameTimeInSeconds = gameResult.gameSettings.gameTimeInSeconds,
                maxSumValue = gameResult.gameSettings.maxSumValue
            )
        )
}