package com.example.dicoding_jetpack.utils

import android.content.Context
import android.util.Log
import com.example.dicoding_jetpack.data.source.remote.response.ContentResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {
    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            Log.e("ERR",ex.toString())
            null
        }
    }

    fun loadMovie(): List<ContentResponse> {
        val list = ArrayList<ContentResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("movies.json").toString())
            val listArray = responseObject.getJSONArray("movie")
            for (i in 0 until listArray.length()) {
                val course = listArray.getJSONObject(i)

                val id = course.getString("id")
                val title = course.getString("title")
                val releaseDate=course.getString("release_date")
                val description = course.getString("overview")
                val voteAverage=course.getString("vote_average").toString()
                val originalLanguage = course.getString("original_language")
                val poster = course.getString("poster_path")

                val courseResponse =
                    ContentResponse(
                        id,
                        title,
                        releaseDate,
                        description,
                        voteAverage,
                        originalLanguage,
                        poster
                    )
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e("ERR",e.toString())
        }

        return list
    }
    fun loadTv(): List<ContentResponse> {
        val list = ArrayList<ContentResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("tv_shows.json").toString())
            val listArray = responseObject.getJSONArray("tv_show")
            for (i in 0 until listArray.length()) {
                val course = listArray.getJSONObject(i)

                val id = course.getString("id")
                val title = course.getString("name")
                val releaseDate=course.getString("first_air_date")
                val description = course.getString("overview")
                val voteAverage=course.getString("vote_average")
                val originalLanguage = course.getString("original_language")
                val poster = course.getString("poster_path")

                val courseResponse =
                    ContentResponse(
                        id,
                        title,
                        releaseDate,
                        description,
                        voteAverage,
                        originalLanguage,
                        poster
                    )
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }


}