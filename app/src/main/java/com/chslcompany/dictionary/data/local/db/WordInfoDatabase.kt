package com.chslcompany.dictionary.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chslcompany.dictionary.data.local.entity.WordInfoDbEntity


@Database(
    entities = [WordInfoDbEntity::class],
    version = 1
)

abstract class WordInfoDatabase : RoomDatabase() {

    abstract val dao : WordInfoDao


}