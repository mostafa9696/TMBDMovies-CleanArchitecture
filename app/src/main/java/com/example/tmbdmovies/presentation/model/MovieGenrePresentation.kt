package com.example.tmbdmovies.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieGenrePresentation(
    val id: Int,
    val name: String? = null
): Parcelable