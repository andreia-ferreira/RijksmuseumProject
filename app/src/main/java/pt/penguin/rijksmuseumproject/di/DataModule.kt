package pt.penguin.rijksmuseumproject.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pt.penguin.data.repository.MuseumRepositoryImpl
import pt.penguin.domain.repository.MuseumRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindMuseumRepository(museumRepositoryImpl: MuseumRepositoryImpl): MuseumRepository
}