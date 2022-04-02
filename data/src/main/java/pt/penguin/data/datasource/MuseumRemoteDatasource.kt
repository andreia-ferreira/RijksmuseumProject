package pt.penguin.data.datasource

import pt.penguin.data.model.home.MuseumDataModel
import pt.penguin.common.Result
import pt.penguin.data.model.details.ArtworkDataModel

interface MuseumRemoteDatasource {
    suspend fun getCollection(): Result<MuseumDataModel>
    suspend fun getArtworkDetails(objectNumber: String): Result<ArtworkDataModel>
}