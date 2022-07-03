package com.example.tmbdmovies.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCastPresentation(
    val profileImageUrl: String? = null,
    val name: String? = null,
    val characterFilmName: String? = null,
): Parcelable