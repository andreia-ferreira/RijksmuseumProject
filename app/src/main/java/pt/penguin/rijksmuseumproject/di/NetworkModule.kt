package pt.penguin.rijksmuseumproject.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pt.penguin.data.datasource.MuseumRemoteDatasource
import pt.penguin.datasource.MuseumContentService
import pt.penguin.datasource.MuseumRemoteDatasourceImpl
import pt.penguin.rijksmuseumproject.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Reusable
    @Provides
    fun providesRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Reusable
    @Provides
    fun providesMuseumContentService(
        retrofit: Retrofit
    ): MuseumContentService = retrofit
        .newBuilder()
        .build()
        .create(MuseumContentService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
interface DatasourceModule {
    @Binds
    fun bindMuseumRemoteDatasource(museumRemoteDatasource: MuseumRemoteDatasourceImpl): MuseumRemoteDatasource
}