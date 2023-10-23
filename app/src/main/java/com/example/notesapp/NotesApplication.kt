package com.example.notesapp

import android.app.Application
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp
import dagger.multibindings.Multibinds

@HiltAndroidApp
class NotesApplication: Application() {

}