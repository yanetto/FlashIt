package com.yanetto.flashit.ui.screens.editscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanetto.core.domain.model.Card
import com.yanetto.core.domain.repository.LocalDataSourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditScreenViewModel @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(EditScreenUiState())
    val uiState: StateFlow<EditScreenUiState> = _uiState.asStateFlow()

    fun changeQuestion(question: String) {
        _uiState.update { currentState ->
            currentState.copy(currentCard = _uiState.value.currentCard.copy(question = question))
        }
    }

    fun changeAnswer(answer: String) {
        _uiState.update { currentState ->
            currentState.copy(currentCard = _uiState.value.currentCard.copy(answer = answer))
        }
    }

    fun updateCardId(cardId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            localDataSourceRepository.getCardByIdFlow(cardId)
                .collect { card ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            currentCard = _uiState.value.currentCard.copy(
                                id = cardId,
                                question = card.question,
                                answer = card.answer,
                                setId = card.setId
                            )
                        )
                    }
                }
        }
    }

    fun updateSetId(setId: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentCard = _uiState.value.currentCard.copy(setId = setId)
            )
        }
    }

    fun addCard(card: Card) {
        Log.d("NEW_CARD", card.toString())
        viewModelScope.launch(Dispatchers.IO) {
            localDataSourceRepository.insertCard(
                Card(
                    question = card.question,
                    answer = card.answer,
                    setId = card.setId
                )
            )
        }
    }

    fun updateCard(card: Card) {
        Log.d("UPDATE_CARD", card.toString())
        viewModelScope.launch(Dispatchers.IO) {
            localDataSourceRepository.updateCard(card)
        }
    }

    fun deleteCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            localDataSourceRepository.deleteCard(card)
        }
    }
}