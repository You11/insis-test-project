package ru.you11.insistestproject.main.addnote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddNoteViewModel: ViewModel() {

    val cardColor = MutableLiveData<String>()

    fun setCardColor(hex: String) {
        cardColor.postValue(hex)
    }

    fun getCardColor() = cardColor.value

    fun isTitleInputValid(input: String): Boolean = isInputValid(input)

    fun isDescriptionInputValid(input: String): Boolean = isInputValid(input)

    private fun isInputValid(input: String): Boolean = input.isNotBlank()
}