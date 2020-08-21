package com.example.dicoding_jetpack.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dicoding_jetpack.data.source.local.entity.LocalData

@Database(entities = [LocalData::class],
    version = 1,
    exportSchema = false)
abstract class CatalogDatabase: RoomDatabase() {
    abstract fun catalogDao(): CatalogDao

    companion object {

        @Volatile
        private var INSTANCE: CatalogDatabase? = null

        fun getInstance(context: Context): CatalogDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    CatalogDatabase::class.java,
                    "Catalog.db").build()
            }
    }

}