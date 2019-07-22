package ru.you11.insistestproject.models

data class Note(
    val name: String,
    val description: String,
    val color: Color = Color.WHITE
)