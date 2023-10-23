package com.example.notesapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.model.NoteModel
import com.example.notesapp.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {

//    private val _noteState = MutableStateFlow<NoteModel?>(null)
//    val noteState = _noteState.asStateFlow()
//
//    fun getNote(id: Long) {
//        viewModelScope.launch {
//            _noteState.value = noteRepository.getNoteByID(id)
//
//        }
//    }

    fun getNote(id: Long) = flow {
        emit(noteRepository.getNoteByID(id))
    }

}