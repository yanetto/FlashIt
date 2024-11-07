package com.yanetto.flashit.ui.screens.cardscreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CardScreenViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(CardScreenUiState())
    val uiState: StateFlow<CardScreenUiState> = _uiState.asStateFlow()

}