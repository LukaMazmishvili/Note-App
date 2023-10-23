package com.example.notesapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.data.model.NoteModel
import com.example.notesapp.databinding.FragmentNoteBinding
import com.example.notesapp.ui.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteViewModel by viewModels()

    private val args: NoteFragmentArgs by navArgs()

    private var noteModel: NoteModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeners()
        observer(args.noteId)
//        updateUI(noteModel)

    }

    private fun listeners() {
        with(binding) {
            ivBtnBack.setOnClickListener {
                activity?.supportFragmentManager?.popBackStack()
            }

            ivEditNote.setOnClickListener {
                findNavController().navigate(NoteFragmentDirections.actionNoteFragmentToEditorFragment(args.noteId))
            }
        }
    }

    private fun observer(noteId: Long) {
        if (noteId != -1L)  {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.getNote(noteId).collect {
                        withContext(Dispatchers.Main) {
                            updateUI(it)
                        }
                    }
                }
            }
        }
    }

    private fun updateUI(note: NoteModel?) {
        with(binding) {
            note?.let {
                tvTitle.text = note.title
                tvNoteText.text = note.content
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}