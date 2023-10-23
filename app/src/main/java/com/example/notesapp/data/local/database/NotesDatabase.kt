package com.example.notesapp.data.local.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesapp.data.model.NoteModel
import com.example.notesapp.data.local.dao.NoteDao

@Database(entities = [NoteModel::class], version = 2)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}