package com.rs.flickd.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rs.flickd.BuildConfig.API_KEY
import com.rs.flickd.BuildConfig.BASE_URL
import com.rs.flickd.repository.Constants
import com.rs.flickd.repository.TMDBApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private class ApiKeyInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url().newBuilder().addQueryParameter("api_key", API_KEY).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(ApiKeyInterceptor())
                    .connectTimeout(Constants.TMDB_API_TIMEOUT_CONNECT, TimeUnit.SECONDS)
                    .readTimeout(Constants.TMDB_API_TIMEOUT_READ, TimeUnit.SECONDS)
                    .build()


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()

    @Provides
    @Singleton
    fun provideTMDBApi(retrofit: Retrofit): TMDBApi =
            retrofit.create(TMDBApi::class.java)

}