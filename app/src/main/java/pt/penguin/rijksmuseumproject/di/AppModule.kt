package pt.penguin.rijksmuseumproject.di

import android.app.Activity
import android.app.Application
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pt.penguin.domain.repository.MuseumRepository
import pt.penguin.rijksmuseumproject.MainMuseumActivity

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

}
