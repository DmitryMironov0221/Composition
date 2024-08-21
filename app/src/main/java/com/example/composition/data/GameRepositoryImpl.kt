package com.example.composition.data


import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.entity.Question
import com.example.composition.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random
class GameRepositoryImpl(application: Application) : GameRepository {
    private val gameResultListLD = MutableLiveData<List<GameResult>>()
    private val gameResultList = sortedSetOf<GameResult>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private var autoIncrementId = 0
    private val dao = AppDataBase.getInstance(application).gameResultListDao()
    private val mapper = Mapper()
    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList())
    }



    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(
                    10,
                    3,
                    50,
                    8
                )
            }
            Level.EASY -> {
                GameSettings(
                    10,
                    10,
                    70,
                    60
                )
            }
            Level.NORMAL -> {
                GameSettings(
                    20,
                    20,
                    80,
                    40
                )
            }
            Level.HARD -> {
                GameSettings(
                    30,
                    30,
                    90,
                    45
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getGameResultList(): LiveData<List<GameResult>> = MediatorLiveData<List<GameResult>>().apply {
        addSource(dao.getGameResultList()){
            value = it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getGameResult(id: Int): GameResult {
        val dbModel = dao.getGameResult(id)
        return mapper.mapDbModelToEntity(dbModel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun addGameResult(gameResult: GameResult) {
        withContext(Dispatchers.IO){
            dao.addGameResult(mapper.mapEntityToDbModel(gameResult))
        }
    }


    companion object{
        private const val MIN_SUM_VALUE = 2
        private const val MIN_ANSWER_VALUE = 1
    }
}