package com.example.composition.presentation

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.composition.data.GameRepositoryImpl
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.usecases.GetGameResultListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameResultsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GameRepositoryImpl(application)
    private val getGameResultListUseCase = GetGameResultListUseCase(repository)

    val gameResultList: LiveData<List<GameResult>> = getGameResultListUseCase.getGameResult()
    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult> = _gameResult

    @RequiresApi(Build.VERSION_CODES.O)
    fun getGameResult(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _gameResult.postValue( repository.getGameResult(id))
        }
    }

}