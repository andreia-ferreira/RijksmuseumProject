package pt.penguin.domain.repository

import pt.penguin.common.Result
import pt.penguin.domain.model.details.ArtworkDetails
import pt.penguin.domain.model.home.ArtObjectDetails
import pt.penguin.domain.model.home.MuseumCollection

interface MuseumRepository {
    suspend fun getMuseumCollection(): Result<List<ArtObjectDetails>>
    suspend fun getArtworkDetails(objectNumber: String): Result<ArtworkDetails>
}