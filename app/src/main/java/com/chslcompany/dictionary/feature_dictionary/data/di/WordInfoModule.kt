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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
    fun provideOKHttp(): OkHttpClient =
        OkHttpClient.Builder().apply {
            connectTimeout(60L, TimeUnit.SECONDS)
            readTimeout(60L, TimeUnit.SECONDS)
            writeTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                )
        }.build()


    @Provides
    @Singleton
    fun provideRetrofit(): DictionaryApi =
        Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOKHttp())
            .build()
            .create(DictionaryApi::class.java)
}