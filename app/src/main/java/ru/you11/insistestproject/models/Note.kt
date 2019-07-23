package ru.you11.insistestproject.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Note(
    val id: Int,
    val name: String,
    val description: String,
    val color: Color = Color.WHITE
): Parcelable