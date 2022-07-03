package com.example.tmbdmovies.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieTrailerPresentation(
    val name: String? = null,
    val key: String? = null,
    val official: Boolean? = null
): Parcelable