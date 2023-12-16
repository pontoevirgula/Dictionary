package com.chslcompany.dictionary.feature_dictionary.domain.repository

import com.chslcompany.dictionary.feature_dictionary.data.local.db.WordInfoDao
import com.chslcompany.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.chslcompany.dictionary.util.Resource
import com.chslcompany.dictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())
        val wordInfo = getCacheWordInfos(word)
        emit(Resource.Loading(data = wordInfo))

        try {
            val service = api.getWord(word)
            dao.deleteWordInfo(service.map { it.word })
            dao.insertWord(service.map { it.toWordInfoDb() })
            val newWordsInfo = getCacheWordInfos(word)
            emit(Resource.Success(data = newWordsInfo))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = SERVER_ERROR,
                    data = wordInfo
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = INTERNET_ERROR,
                    data = wordInfo
                )
            )
        }

    }

    private suspend fun getCacheWordInfos(word: String): List<WordInfo> {
        val wordInfo = dao.listWordInfo(word).map {
            it.toWordInfo()
        }
        return wordInfo
    }

    companion object{
        private const val SERVER_ERROR = "Detectamos algumas instabilidades.\nVolte novamente mais tarde."
        private const val INTERNET_ERROR = "Não foi possível se conectar.\n Verifique sua internet."
    }
}