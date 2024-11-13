package com.yanetto.flashit.ui.screens.cardsetgridscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanetto.flashit.data.mapper.toCard
import com.yanetto.flashit.data.repository.LocalDataSourceRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardSetGridScreenViewModel @Inject constructor(
    private val localDataSourceRepositoryImpl: LocalDataSourceRepositoryImpl
): ViewModel() {
    private val _uiState = MutableStateFlow(CardSetGridScreenUiState())
    val uiState: StateFlow<CardSetGridScreenUiState> = _uiState.asStateFlow()

    fun updateSetId(setId: Int) {
        _uiState.update { currentState ->
            currentState.copy(setId = setId)
        }

        viewModelScope.launch(Dispatchers.IO) {
            localDataSourceRepositoryImpl.getCardSetWithCards(setId)
                .collect { cardSetWithCards ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            cards = cardSetWithCards.cards.map { it.toCard() }
                        )
                    }
                }
        }

        viewModelScope.launch(Dispatchers.IO) {
            localDataSourceRepositoryImpl.getCardSetByIdFlow(setId).collect { cardSet ->
                _uiState.update { currentState ->
                    currentState.copy(setName = cardSet.name)
                }
            }
        }
    }
}