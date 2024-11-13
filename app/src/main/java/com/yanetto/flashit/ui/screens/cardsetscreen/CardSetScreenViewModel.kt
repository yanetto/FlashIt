package com.yanetto.flashit.ui.screens.cardsetscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanetto.flashit.data.repository.LocalDataSourceRepositoryImpl
import com.yanetto.flashit.domain.model.CardSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardSetScreenViewModel @Inject constructor(
    private val localDataSourceRepositoryImpl: LocalDataSourceRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow(CardSetScreenUiState())
    val uiState: StateFlow<CardSetScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            localDataSourceRepositoryImpl.getAllCardSetsFlow().collect { cardSets ->
                _uiState.update { currentState ->
                    currentState.copy(cardSets = cardSets)
                }
            }
        }
    }

    fun changeName(name: String) {
        _uiState.update { uis ->
            uis.copy(newName = name)
        }
    }

    fun addSet(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            localDataSourceRepositoryImpl.insertCardSet(CardSet(name = name))
        }
    }

    fun setCurrentCardSet(cardSet: CardSet) {
        _uiState.update { currentState ->
            currentState.copy(
                currentSet = cardSet
            )
        }
    }

    fun deleteSet(cardSet: CardSet) {
        viewModelScope.launch(Dispatchers.IO) {
            localDataSourceRepositoryImpl.deleteCardSet(cardSet)
        }
    }
}