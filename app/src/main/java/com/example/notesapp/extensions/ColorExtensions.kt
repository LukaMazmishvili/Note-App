package com.example.notesapp.extensions

import android.graphics.Color
import kotlin.random.Random

fun getRandomColor(): Int {
    val red = Random.nextInt(150, 256)
    val green = Random.nextInt(150, 256)
    val blue = Random.nextInt(150, 256)
    return Color.rgb(red, green, blue)
}