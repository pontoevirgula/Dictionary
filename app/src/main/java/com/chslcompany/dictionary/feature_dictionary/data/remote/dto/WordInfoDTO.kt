package com.chslcompany.dictionary.feature_dictionary.data.remote.dto

import com.chslcompany.dictionary.feature_dictionary.data.local.entity.WordInfoDbEntity
import com.chslcompany.dictionary.feature_dictionary.domain.model.Definition
import com.chslcompany.dictionary.feature_dictionary.domain.model.LicensePhonetic
import com.chslcompany.dictionary.feature_dictionary.domain.model.Meaning
import com.chslcompany.dictionary.feature_dictionary.domain.model.Phonetic

data class WordInfoDTO(
    val meanings: List<MeaningDto>,
    val origin : String?,
    val phonetic : String?,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
){
    fun toWordInfoDb() : WordInfoDbEntity =
        WordInfoDbEntity(
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic ?: "",
            origin = origin ?: "",
            word = word
        )
}

data class MeaningDto(
    val antonyms: List<String>,
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
    val synonyms: List<String>
){
    fun toMeaning() : Meaning =
        Meaning(
            antonyms = antonyms,
            definitions = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech,
            synonyms = synonyms
        )
}

data class DefinitionDto(
    val antonyms: List<Any>,
    val definition: String,
    val example: String,
    val synonyms: List<Any>
){
    fun toDefinition() : Definition =
        Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
}



data class PhoneticDto(
    val audio: String,
    val license: LicensePhoneticDto,
    val sourceUrl: String,
    val text: String
){
    fun toPhonetic() : Phonetic =
        Phonetic(
          audio = audio,
            license = license.toLicensePhonetic(),
            sourceUrl = sourceUrl,
            text = text
        )
}

data class LicensePhoneticDto(
    val name: String,
    val url: String
){
    fun toLicensePhonetic() : LicensePhonetic =
        LicensePhonetic(
            name = name,
            url = url
        )
}