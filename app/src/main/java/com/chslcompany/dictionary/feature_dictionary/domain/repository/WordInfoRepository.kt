package com.chslcompany.dictionary.feature_dictionary.domain.repository

import com.chslcompany.dictionary.core.util.Resource
import com.chslcompany.dictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}


