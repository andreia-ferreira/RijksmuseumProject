package pt.penguin.datasource

import pt.penguin.common.Result
import pt.penguin.configuration.BuildConfig
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
    override suspend fun loadCollection(pageNumber: Int): Result<MuseumDataModel> {
        val museumApiModel = museumContentService.getCollection(
            key = BuildConfig.API_KEY,
            sorting = SORTING_KEY,
            page = pageNumber
        )
        return try {
            Result.Success(museumApiMapper.mapToData(museumApiModel.body()))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getArtworkDetails(objectNumber: String): Result<ArtworkDataModel> {
        val result = museumContentService.getArtworkDetails(
            key = BuildConfig.API_KEY,
            objectNumber = objectNumber
        )
        return try {
            Result.Success(artworkApiMapper.mapToData(result.body()))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    companion object {
        const val SORTING_KEY = "artist"
    }
}