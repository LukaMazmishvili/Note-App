package com.example.notesapp.di

import android.content.Context
import androidx.room.Room
import com.example.notesapp.data.local.dao.NoteDao
import com.example.notesapp.data.local.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {

    private var INSTANCE: NotesDatabase? = null

    @Provides
    @Singleton
    fun provideNotesDatabase(@ApplicationContext context: Context) : NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "app_database"
        ).build()

//        INSTANCE ?: synchronized(this) {
//            val instance = Room.databaseBuilder(
//                context.applicationContext,
//                NotesDatabase::class.java,
//                "note_database"
//            ).build()
//            INSTANCE = instance
//            instance
//        }
    }

    @Provides
    @Singleton
    fun provideNoteDao(notesDatabase: NotesDatabase) : NoteDao {
        return notesDatabase.noteDao()
    }

}