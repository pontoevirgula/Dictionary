package com.chslcompany.dictionary.presentation

import com.chslcompany.dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems : List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
