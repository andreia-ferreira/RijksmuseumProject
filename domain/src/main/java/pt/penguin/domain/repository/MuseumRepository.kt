package pt.penguin.domain.repository

import pt.penguin.common.Result
import pt.penguin.domain.model.MuseumCollection

interface MuseumRepository {
    suspend fun getMuseumCollection(): Result<MuseumCollection>
}