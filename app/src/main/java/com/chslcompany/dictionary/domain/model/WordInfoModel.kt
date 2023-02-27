package com.chslcompany.dictionary.domain.model

data class WordInfo(
    val license: License,
    val meanings: List<Meaning>,
    val phonetics: List<Phonetic>,
    val sourceUrls: List<String>,
    val word: String
)

data class License(
    val name: String,
    val url: String
)


data class Phonetic(
    val audio: String,
    val license: LicensePhonetic,
    val sourceUrl: String,
    val text: String
)

data class LicensePhonetic(
    val name: String,
    val url: String
)


data class Meaning(
    val antonyms: List<String>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<String>
)

data class Definition(
    val antonyms: List<Any>,
    val definition: String,
    val example: String,
    val synonyms: List<Any>
)
