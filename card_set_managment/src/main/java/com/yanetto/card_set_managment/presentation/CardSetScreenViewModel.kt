package com.yanetto.card_set_managment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanetto.card_set_managment.domain.DeleteSetUseCase
import com.yanetto.card_set_managment.domain.GetAllSetsUseCase
import com.yanetto.card_set_managment.domain.InsertSetUseCase
import com.yanetto.card_set_managment.domain.UpdateSetUseCase
import com.yanetto.core.domain.model.CardSet
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
    private val deleteSetUseCase: DeleteSetUseCase,
    private val updateSetUseCase: UpdateSetUseCase,
    private val insertSetUseCase: InsertSetUseCase,
    getAllSetsUseCase: GetAllSetsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(CardSetScreenUiState())
    val uiState: StateFlow<CardSetScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getAllSetsUseCase()
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
            insertSetUseCase(cardSet)
        }
    }

    fun updateSet(cardSet: CardSet) {
        viewModelScope.launch(Dispatchers.IO) {
            updateSetUseCase(cardSet)
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
            deleteSetUseCase(cardSet)
        }
    }
}