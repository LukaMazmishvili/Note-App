package com.example.notesapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.model.NoteModel
import com.example.notesapp.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {

    fun getNotes() = flow {
        noteRepository.getAllNotes().collect {
                emit(it)
        }
    }

    fun deleteNote(noteModel: NoteModel) {
        viewModelScope.launch {
            noteRepository.deleteNote(noteModel)
        }
    }
}