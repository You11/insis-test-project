package ru.you11.insistestproject.other

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.you11.insistestproject.models.Note


class JsonNoteGetter(context: Context) {

    private val assets: AssetManager = context.assets

    fun getJSONArrayListFromFile(path: String): ArrayList<Note> {

        val jsonString = assets.open(path).bufferedReader().use {
            it.readText()
        }

        return Gson().fromJson(jsonString, object: TypeToken<ArrayList<Note>>() {}.type)
    }
}