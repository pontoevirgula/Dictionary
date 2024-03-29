package com.chslcompany.dictionary.feature_dictionary.domain.usecase

import com.chslcompany.dictionary.core.util.Resource
import com.chslcompany.dictionary.feature_dictionary.domain.model.WordInfo
import com.chslcompany.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUseCase(private val repository: WordInfoRepository) {

    operator fun invoke(word : String) : Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()){
            return flow {  }
        }
        return repository.getWordInfo(word)
    }
}