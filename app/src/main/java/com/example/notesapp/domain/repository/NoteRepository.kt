package com.example.notesapp.domain.repository

import com.example.notesapp.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun getAllNotes() : Flow<List<NoteModel>>
    suspend fun getNoteByID(id: Long) : NoteModel
    suspend fun saveNote(note: NoteModel)
    suspend fun deleteNote(note: NoteModel)
    suspend fun updateNote(note: NoteModel)
}