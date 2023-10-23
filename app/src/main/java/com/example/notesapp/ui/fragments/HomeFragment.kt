package com.example.notesapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.ui.adapters.NotesAdapter
import com.example.notesapp.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val adapter by lazy {
        NotesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        observer()
        listeners()
        itemClicked()

    }

    private fun listeners() {
        with(binding) {
            fabAddNewNote.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToEditorFragment())
            }
        }
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getNotes().collect {
                    Log.d("NotesList", it.toString())
                    adapter.submitList(it.toList())
                }
            }
        }
    }

    private fun itemClicked() {
        adapter.onItemClick = { noteModel ->
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToNoteFragment(
                    noteModel.id
                )
            )

//            viewModel.deleteNote(noteModel)
        }
    }

    private fun setUpRecyclerView() {
        binding.rvTodos.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}