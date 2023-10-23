package com.example.notesapp.data.local.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteModel)

    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<NoteModel>>

    @Query("SELECT * FROM notes where id = :id")
    fun getNoteById(id: Long): NoteModel

    @Query("SELECT * FROM notes WHERE title like :searchWord ")
    fun getNotes(searchWord: String): Flow<List<NoteModel>>

    @Update
    suspend fun updateNote(note: NoteModel)

    @Delete
    suspend fun deleteNote(note: NoteModel)

}