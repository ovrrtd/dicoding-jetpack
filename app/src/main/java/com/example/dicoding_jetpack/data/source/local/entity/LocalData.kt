package com.example.dicoding_jetpack.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localentities")
data class LocalData(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "userScore")
    var userScore: String,
    @ColumnInfo(name = "type")
    var type: Boolean ,
    @ColumnInfo(name = "imagePath")
    var imagePath: String,
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,
    @ColumnInfo(name= "bookmarked" )
    var bookmarked: Boolean = false
)
