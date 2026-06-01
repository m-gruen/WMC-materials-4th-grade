package com.example.myapplication

data class Note(
    val id: Long = 0,
    val text: String,
    val createdAt: Long = System.currentTimeMillis()
)
