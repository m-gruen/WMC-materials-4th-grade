package com.example.myapplication

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class Home(
    val title: String
) : NavKey

@Serializable
data class Profile(
    val title: String
) : NavKey

@Serializable
data object NoteList : NavKey

@Serializable
data class NoteDetail(val noteId: Int) : NavKey
