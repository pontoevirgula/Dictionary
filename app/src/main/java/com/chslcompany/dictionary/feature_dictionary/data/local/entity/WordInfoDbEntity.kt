package com.chslcompany.dictionary.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chslcompany.dictionary.feature_dictionary.domain.model.License
import com.chslcompany.dictionary.feature_dictionary.domain.model.Meaning
import com.chslcompany.dictionary.feature_dictionary.domain.model.Phonetic
import com.chslcompany.dictionary.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoDbEntity(
    val meanings: List<Meaning>,
    val phonetic: String,
    val origin: String,
    val word: String,
    @PrimaryKey val id : Int? = null
){
    fun toWordInfo() : WordInfo =
        WordInfo(
            meanings = meanings,
            phonetic = phonetic,
            origin = origin,
            word = word
        )
}
