package com.example.codingchallenge.core.di

import android.content.Context
import com.example.codingchallenge.core.di.Constants.HEADER_CACHE_CONTROL
import com.example.codingchallenge.core.di.Constants.HEADER_PRAGMA
import com.example.codingchallenge.data.RandomUserApiService
import com.example.codingchallenge.data.data_source.local.NetworkConnectivity
import com.example.codingchallenge.data.repository.UserRepositoryImpl
import com.example.codingchallenge.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): RandomUserApiService {
        return lazy {
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RandomUserApiService::class.java)
        }.value
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: RandomUserApiService): UserRepository {
        return UserRepositoryImpl(
            api = api
        )
    }

    @Provides
    @Singleton
    fun providesOkhttpClient(@ApplicationContext context: Context): OkHttpClient {
        val interceptor = Interceptor { chain ->
            var request = chain.request()
            if (!NetworkConnectivity(context).hasInternet()) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(1, TimeUnit.DAYS)
                    .build()

                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
        val httpCacheDirectory = File(context.cacheDir, "offlineCache")
        val cacheSize = 50 * 1024 * 1024
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())

        return lazy {
            OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
        }.value
    }
}