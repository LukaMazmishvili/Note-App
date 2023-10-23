package com.example.notesapp.ui.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.model.NoteModel
import com.example.notesapp.databinding.ItemNoteBinding

class NotesAdapter : ListAdapter<NoteModel, NotesAdapter.ViewHolder>(NotesDiffUtil()) {

    var onItemClick: ((NoteModel) -> Unit)? = null

    inner class ViewHolder(binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.tvTitle
        val note = binding.tvTitle
        val noteItem = binding.root
    }

    class NotesDiffUtil() : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.title.text = item.title
        holder.note.backgroundTintList = ColorStateList.valueOf(item.backgroundColor)

        holder.noteItem.setOnClickListener {
            onItemClick?.invoke(item)
        }

    }

}