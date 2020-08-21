package com.example.dicoding_jetpack.data

data class TvShowData (
    var id: String,
    var title: String,
    var overview: String,
    var userScore : String,
    var type: Boolean = false,
    var imagePath: String,
    var releaseDate : String,
    var bookmarked: Boolean = false
)