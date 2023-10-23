package com.example.notesapp.di

import com.example.notesapp.data.local.dao.NoteDao
import com.example.notesapp.data.repository.NoteRepositoryImpl
import com.example.notesapp.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao) : NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }

}