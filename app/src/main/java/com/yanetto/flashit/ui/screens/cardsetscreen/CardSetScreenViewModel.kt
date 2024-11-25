package com.yanetto.flashit.ui.screens.cardsetscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanetto.flashit.core.domain.model.CardSet
import com.yanetto.flashit.core.domain.repository.LocalDataSourceRepository
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
    private val localDataSourceRepository: LocalDataSourceRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CardSetScreenUiState())
    val uiState: StateFlow<CardSetScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            localDataSourceRepository.getAllCardSetsFlow()
                .collect { cardSets ->
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

    fun addSet(cardSet: CardSet) {
        viewModelScope.launch(Dispatchers.IO) {
            localDataSourceRepository.insertCardSet(CardSet(name = cardSet.name))
        }
    }

    fun updateSet(cardSet: CardSet) {
        viewModelScope.launch(Dispatchers.IO) {
            localDataSourceRepository.updateCardSet(cardSet)
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
            localDataSourceRepository.deleteCardSetWithCards(cardSet)
        }
    }
}