package com.example.notesapp.data.repository

import com.example.notesapp.data.local.dao.NoteDao
import com.example.notesapp.data.model.NoteModel
import com.example.notesapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao): NoteRepository {
    override suspend fun getAllNotes(): Flow<List<NoteModel>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteByID(id: Long): NoteModel {
        return  noteDao.getNoteById(id)
    }

    override suspend fun saveNote(note: NoteModel) {
        return noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: NoteModel) {
        return noteDao.deleteNote(note)
    }

    override suspend fun updateNote(note: NoteModel) {
        return noteDao.updateNote(note)
    }
}