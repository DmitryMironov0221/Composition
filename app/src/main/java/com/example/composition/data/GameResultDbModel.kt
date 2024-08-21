package com.example.composition.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity(tableName = "game_settings")
data class GameSettingsDbModel (
    @PrimaryKey(autoGenerate = true)
    val gameSettingsId: Int = 0,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int,
    val maxSumValue: Int
)

@Entity(tableName = "game_result")
data class GameResultDbModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val dateTime: LocalDateTime,
    @Embedded
    val gameSettings: GameSettingsDbModel
)