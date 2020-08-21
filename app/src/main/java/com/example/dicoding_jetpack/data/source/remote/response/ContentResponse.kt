package com.example.dicoding_jetpack.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentResponse (
     val id: String,
     val title: String,
     val releaseDate: String,
     val description: String,
     val voteAverage: String,
     val originalLanguage: String,
     val poster: String
    ): Parcelable