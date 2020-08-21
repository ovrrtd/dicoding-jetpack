package com.example.dicoding_jetpack.data

data class MovieData(
    var id: String,
    var title: String,
    var overview: String,
    var userScore: String,
    var type: Boolean = true,
    var imagePath: String,
    var releaseDate: String,
    var bookmarked: Boolean = false
)