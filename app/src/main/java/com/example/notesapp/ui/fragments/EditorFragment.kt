package com.example.notesapp.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.data.model.NoteModel
import com.example.notesapp.databinding.FragmentEditorBinding
import com.example.notesapp.extensions.getRandomColor
import com.example.notesapp.ui.viewmodels.EditorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class EditorFragment : Fragment() {

    private var _binding: FragmentEditorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EditorViewModel by viewModels()

    private val args: EditorFragmentArgs by navArgs()

    private var noteModel: NoteModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeners()
        observer(args.noteId)
//        updateUi(noteModel)

    }

    private fun listeners() {
        with(binding) {
            ivBtnBack.setOnClickListener {
                activity?.supportFragmentManager?.popBackStack()
            }

            ivSave.setOnClickListener {
                val title = tvTitle.text.toString().trim()
                val noteText = tvNoteText.text.toString().trim()

                if (title.isNotEmpty() && noteText.isNotEmpty()) {
                    if (args.noteId != -1L) {
                        updateNote(args.noteId, title, noteText)
                    } else {
                        saveNote(title, noteText)
                    }
                }
            }
        }
    }

    private fun saveNote(title: String, noteText: String) {
        with(binding) {
            val note = NoteModel( title = title, content = noteText, backgroundColor = getRandomColor())

            viewModel.saveNote(note)

            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun updateNote(id: Long, title: String, noteText: String) {
        val note = NoteModel(id = id, title = title, content = noteText, backgroundColor = getRandomColor())

        viewModel.updateNote(note)

        activity?.supportFragmentManager?.popBackStack()
    }

    private fun observer(noteId: Long) {
        if (noteId != -1L)  {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.getNote(noteId).collect {
                        withContext(Dispatchers.Main) {
                            updateUi(it)
                        }
                    }
                }
            }
        }
    }

    private fun updateUi(note: NoteModel?) {
        with(binding) {
            note?.let {
                tvTitle.setText(note.title)
                tvNoteText.setText(note.content)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}