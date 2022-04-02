package pt.penguin.datasource

import pt.penguin.common.Result
import pt.penguin.data.datasource.MuseumRemoteDatasource
import pt.penguin.data.model.details.ArtworkDataModel
import pt.penguin.data.model.home.MuseumDataModel
import pt.penguin.datasource.mapper.details.ArtworkApiMapper
import pt.penguin.datasource.mapper.home.MuseumApiMapper
import javax.inject.Inject

class MuseumRemoteDatasourceImpl @Inject constructor(
    private val museumContentService: MuseumContentService,
    private val museumApiMapper: MuseumApiMapper,
    private val artworkApiMapper: ArtworkApiMapper,
): MuseumRemoteDatasource {
    override suspend fun getCollection(): Result<MuseumDataModel> {
        val museumApiModel = museumContentService.getCollection(
            key = "0fiuZFh4",
            resultsPerPage = 10,
            page = 1
        )
        return try {
            Result.Success(museumApiMapper.mapToData(museumApiModel.body()))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getArtworkDetails(objectNumber: String): Result<ArtworkDataModel> {
        val result = museumContentService.getArtworkDetails(
            key = "0fiuZFh4",
            objectNumber
        )
        return try {
            Result.Success(artworkApiMapper.mapToData(result.body()))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}