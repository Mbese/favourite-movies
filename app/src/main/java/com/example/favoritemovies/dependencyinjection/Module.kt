package com.example.favoritemovies.dependencyinjection

import com.example.favoritemovies.repo.FavouriteMoviesRepository
import com.example.favoritemovies.service.ApiClient.Companion.BASE_URL
import com.example.favoritemovies.service.FavouriteMoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {


    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): FavouriteMoviesService =
        retrofit.create(FavouriteMoviesService::class.java)


    @Singleton
    @Provides
    fun provideFavouriteMoviesRepository(apiService: FavouriteMoviesService) =
        FavouriteMoviesRepository(apiService)

//    @Singleton
//    @Provides
//    fun providesIoDispatcher(): CoroutineContext = Dispatchers.IO
}