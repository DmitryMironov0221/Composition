package com.example.composition.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameResultListDao {

    @Query("SELECT * FROM game_result")
    fun getGameResultList():LiveData<List<GameResultDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGameResult(gameResultDbModel: GameResultDbModel)

    @Query("SELECT * FROM game_result WHERE id = :id LIMIT 1")
    fun getGameResult(id: Int): GameResultDbModel





}