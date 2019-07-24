package ru.you11.insistestproject.main.addnote

import androidx.lifecycle.ViewModel

class AddNoteViewModel: ViewModel() {

    fun isTitleInputValid(input: String): Boolean = isInputValid(input)

    fun isDescriptionInputValid(input: String): Boolean = isInputValid(input)

    private fun isInputValid(input: String): Boolean = input.isNotBlank()
}