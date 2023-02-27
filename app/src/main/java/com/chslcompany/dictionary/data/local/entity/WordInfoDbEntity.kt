package com.chslcompany.dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chslcompany.dictionary.domain.model.License
import com.chslcompany.dictionary.domain.model.Meaning
import com.chslcompany.dictionary.domain.model.Phonetic
import com.chslcompany.dictionary.domain.model.WordInfo

@Entity
data class WordInfoDbEntity(
    val license: License,
    val meanings: List<Meaning>,
    val phonetics: List<Phonetic>,
    val sourceUrls: List<String>,
    val word: String,
    @PrimaryKey val id : Int? = null
){
    fun toWordInfo() : WordInfo =
        WordInfo(
            license = license,
            meanings = meanings,
            phonetics = phonetics,
            sourceUrls = sourceUrls,
            word = word
        )
}
