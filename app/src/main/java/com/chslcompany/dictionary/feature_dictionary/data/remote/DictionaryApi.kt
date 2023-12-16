package com.chslcompany.dictionary.feature_dictionary.data.remote

import com.chslcompany.dictionary.feature_dictionary.data.remote.dto.WordInfoDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWord(
        @Path("word") word : String
    ) : List<WordInfoDTO>

    companion object{
        const val BASE_URL = "https://api.dictionary.dev/"
    }
}