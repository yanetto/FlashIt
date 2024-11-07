package com.yanetto.flashit.ui.screens.cardsetscreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CardSetScreenViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(CardSetScreenUiState())
    val uiState: StateFlow<CardSetScreenUiState> = _uiState.asStateFlow()
}