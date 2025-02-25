package com.yanetto.card_learn.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanetto.card_learn.domain.GetCardSetByIdUseCase
import com.yanetto.card_learn.domain.GetCardSetWithCardsUseCase
import com.yanetto.core.data.mapper.toCard
import com.yanetto.core.domain.model.Card
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
class CardScreenViewModel @Inject constructor(
    private val getCardSetWithCardsUseCase: GetCardSetWithCardsUseCase,
    private val getCardSetByIdUseCase: GetCardSetByIdUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(CardScreenUiState())
    val uiState: StateFlow<CardScreenUiState> = _uiState.asStateFlow()

    fun nextCard() {
        if (_uiState.value.currentCardIndex + 1 < _uiState.value.cards.size) {
            _uiState.update { currentState ->
                currentState.copy(
                    currentCardIndex = _uiState.value.currentCardIndex + 1,
                    currentCard = _uiState.value.cards[_uiState.value.currentCardIndex + 1]
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(showFinishScreen = true)
            }
        }
    }

    fun updateSetId(setId: Int) {
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
                    val shuffledCards = cards.shuffled()
                    if (_uiState.value.currentCardIndex < cards.size ) {
                        _uiState.update { currentState ->
                            currentState.copy(
                                cards = shuffledCards,
                                currentCard = shuffledCards[_uiState.value.currentCardIndex]
                            )
                        }
                    }
                }
        }
    }

    fun addCard(card: Card) {
        val newList = _uiState.value.cards.toMutableList()
        newList.add(card)
        _uiState.update { currentState ->
            currentState.copy(cards = newList)
        }
    }
}