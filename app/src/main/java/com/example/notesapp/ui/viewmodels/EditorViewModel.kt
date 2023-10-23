package com.example.notesapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.model.NoteModel
import com.example.notesapp.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {

    fun saveNote(noteModel: NoteModel) {
        viewModelScope.launch {
            noteRepository.saveNote(noteModel)
        }
    }

    fun getNote(noteId: Long) = flow {
        emit(noteRepository.getNoteByID(noteId))
    }

    fun updateNote(noteModel: NoteModel) {
        viewModelScope.launch {
            noteRepository.updateNote(noteModel)
        }
    }

}