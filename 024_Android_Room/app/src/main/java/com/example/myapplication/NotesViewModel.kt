package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider
.AndroidViewModelFactory
.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory


data class NotesUiState(
    val notes: List<Note> = emptyList()
)

class NotesViewModel(
    private val repo: NotesRepository
) : ViewModel() {

    val uiState: StateFlow<NotesUiState> =
        repo.observeAll()
            .map { NotesUiState(notes = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted
                    .WhileSubscribed(5_000),
                initialValue = NotesUiState()
            )

    fun addNote(text: String) {
        viewModelScope.launch {
            repo.add(text)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repo.delete(note)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val app =
                        this[APPLICATION_KEY]!!
                    val dao = AppDatabase
                        .get(app).noteDao()
                    NotesViewModel(
                        NotesRepository(dao)
                    )
                }
            }
    }
}
