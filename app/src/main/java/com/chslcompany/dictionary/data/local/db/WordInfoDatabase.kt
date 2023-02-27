package com.chslcompany.dictionary.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chslcompany.dictionary.data.local.entity.WordInfoDbEntity
import com.chslcompany.dictionary.data.util.Converters


@Database(
    entities = [WordInfoDbEntity::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class WordInfoDatabase : RoomDatabase() {

    abstract val dao : WordInfoDao


}