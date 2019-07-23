package ru.you11.insistestproject.other

import android.os.Bundle
import java.io.Serializable

interface NavigationResult: Serializable {

    fun onNavigationResult(result: Bundle, resultCode: Int)
}