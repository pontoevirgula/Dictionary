package com.chslcompany.dictionary.data.remote.dto

import com.chslcompany.dictionary.domain.model.*

data class WordInfoDTO(
    val license: LicenseDto,
    val meanings: List<MeaningDto>,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
){
    fun toWordInfo() : WordInfo =
        WordInfo(
            license = license.toLicense(),
            meanings = meanings.map { it.toMeaning() },
            phonetics = phonetics.map { it.toPhonetic() },
            sourceUrls = sourceUrls,
            word = word
        )
}

data class LicenseDto(
    val name: String,
    val url: String
){
    fun toLicense() : License =
        License(
            name = name,
            url = url
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