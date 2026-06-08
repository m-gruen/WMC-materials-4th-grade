package com.example.myapplication

import kotlinx.coroutines.flow.Flow

class NotesRepository(
    private val dao: NoteDao
) {
    fun observeAll(): Flow<List<Note>> = dao.observeAll()

    suspend fun add(text: String) {
        dao.insert(Note(text = text))
    }

    suspend fun delete(note: Note) {
        dao.delete(note)
    }
}
