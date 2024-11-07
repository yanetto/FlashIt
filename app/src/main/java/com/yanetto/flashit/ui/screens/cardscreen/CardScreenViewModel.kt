package com.yanetto.flashit.ui.screens.cardscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CardScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CardScreenUiState())
    val uiState: StateFlow<CardScreenUiState> = _uiState.asStateFlow()

}