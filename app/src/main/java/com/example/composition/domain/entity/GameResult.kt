package com.example.composition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class GameResult(
    var id: Int = UNDEFINED_ID,
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val dateTime: LocalDateTime,
    val gameSettings: GameSettings
): Parcelable {
    companion object{
        var UNDEFINED_ID = -1
    }
}