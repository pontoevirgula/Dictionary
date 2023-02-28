package com.chslcompany.dictionary.domain.usecase

import com.chslcompany.dictionary.data.util.Resource
import com.chslcompany.dictionary.domain.model.WordInfo
import com.chslcompany.dictionary.domain.repository.WordInfoRepository
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