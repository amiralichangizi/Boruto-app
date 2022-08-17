package com.tojare.borutoapp.di

import androidx.paging.ExperimentalPagingApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tojare.borutoapp.data.local.BorutoDatabase
import com.tojare.borutoapp.data.remote.BorutoApi
import com.tojare.borutoapp.domain.repository.RemoteDataSource
import com.tojare.borutoapp.domain.repository.RemoteDataSourceImpl
import com.tojare.borutoapp.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                this.setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val mediaType = Json.asConverterFactory("application/json".toMediaType())
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(mediaType)
            .build()


    }

    @Provides
    @Singleton
    fun provideBorutoApi(retrofit: Retrofit): BorutoApi {
        return retrofit.create(BorutoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        borutoApi: BorutoApi,
        borutoDatabase: BorutoDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(borutoApi, borutoDatabase)
    }
}