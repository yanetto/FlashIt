package com.yanetto.card_grid.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanetto.card_grid.domain.GetCardSetByIdUseCase
import com.yanetto.card_grid.domain.GetCardSetWithCardsUseCase
import com.yanetto.core.data.mapper.toCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardSetGridScreenViewModel @Inject constructor(
    private val getCardSetWithCardsUseCase: GetCardSetWithCardsUseCase,
    private val getCardSetByIdUseCase: GetCardSetByIdUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(CardSetGridScreenUiState())
    val uiState: StateFlow<CardSetGridScreenUiState> = _uiState.asStateFlow()

    fun updateSetId(setId: Int) {
        _uiState.update { currentState ->
            currentState.copy(setId = setId)
        }

        viewModelScope.launch(Dispatchers.IO) {
            getCardSetWithCardsUseCase(setId)
                .map { cardSetWithCards ->
                    cardSetWithCards?.cards?.map { it.toCard() } ?: emptyList()
                }
                .catch { exception ->
                    Log.e("CARD_SET_ERROR", exception.message.toString())
                    emit(emptyList())
                }
                .collect { cards ->
                    _uiState.update { currentState ->
                        currentState.copy(cards = cards)
                    }
                }
        }

        viewModelScope.launch(Dispatchers.IO) {
            getCardSetByIdUseCase(setId)
                .map { cardSet ->
                    cardSet?.name ?: ""
                }
                .catch { exception ->
                    Log.e("CARD_SET_ERROR", exception.message.toString())
                    emit("")
                }
                .collect { setName ->
                    _uiState.update { currentState ->
                        currentState.copy(setName = setName)
                    }
                }
        }
    }
}