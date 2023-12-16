package com.chslcompany.dictionary.feature_dictionary.data.di

import android.app.Application
import androidx.room.Room
import com.chslcompany.dictionary.feature_dictionary.data.local.db.WordInfoDatabase
import com.chslcompany.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.chslcompany.dictionary.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.chslcompany.dictionary.feature_dictionary.data.util.Converters
import com.chslcompany.dictionary.feature_dictionary.data.util.GsonParser
import com.chslcompany.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import com.chslcompany.dictionary.feature_dictionary.domain.usecase.GetWordInfoUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideRepository(
        db: WordInfoDatabase,
        api: DictionaryApi
    ): WordInfoRepository = WordInfoRepositoryImpl(api, db.dao)

    @Provides
    @Singleton
    fun provideUseCase(
        repository: WordInfoRepository
    ): GetWordInfoUseCase = GetWordInfoUseCase(repository)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): WordInfoDatabase =
        Room.databaseBuilder(
            app, WordInfoDatabase::class.java, "word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(): DictionaryApi =
        Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
}