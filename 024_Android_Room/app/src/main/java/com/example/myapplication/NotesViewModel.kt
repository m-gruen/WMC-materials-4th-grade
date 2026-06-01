package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class NotesUiState(
    val notes: List<Note> = emptyList()
)

class NotesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState: StateFlow<NotesUiState> = _uiState.asStateFlow()

    fun addNote(text: String) {
        if (text.isBlank()) return
        _uiState.value = _uiState.value.copy(
            notes = _uiState.value.notes + Note(id = 0, text = text)
        )
    }

    fun deleteNote(note: Note) {
        _uiState.value = _uiState.value.copy(
            notes = _uiState.value.notes - note
        )
    }
}
