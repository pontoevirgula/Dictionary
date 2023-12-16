package com.chslcompany.dictionary.feature_dictionary.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chslcompany.dictionary.feature_dictionary.data.local.entity.WordInfoDbEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(infos: List<WordInfoDbEntity>)

    @Query("select * from wordinfodbentity WHERE word LIKE '%' || :word || '%' ")
    suspend fun listWordInfo(word : String) : List<WordInfoDbEntity>

    @Query("DELETE from wordinfodbentity WHERE word IN (:words)")
    suspend fun deleteWordInfo(words : List<String>)

}