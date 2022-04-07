package pt.penguin.data.repository

import pt.penguin.common.Result
import pt.penguin.data.datasource.MuseumMemoryDataSource
import pt.penguin.data.datasource.MuseumRemoteDatasource
import pt.penguin.data.mapper.details.ArtworkDataMapper
import pt.penguin.data.mapper.home.MuseumDataMapper
import pt.penguin.data.model.home.MuseumDataModel
import pt.penguin.domain.model.details.ArtworkDetails
import pt.penguin.domain.model.home.ArtObjectDetails
import pt.penguin.domain.model.home.MuseumCollection
import pt.penguin.domain.repository.MuseumRepository
import java.lang.Exception
import javax.inject.Inject

class MuseumRepositoryImpl @Inject constructor(
    private val museumRemoteDatasource: MuseumRemoteDatasource,
    private val museumMemoryDatasource: MuseumMemoryDataSource,
    private val museumDataMapper: MuseumDataMapper,
    private val artworkDataMapper: ArtworkDataMapper,
): MuseumRepository {

    override suspend fun getMuseumCollection(): Result<List<ArtObjectDetails>> {
        return try {
            val result = if (museumMemoryDatasource.hasValue()) {
                museumMemoryDatasource.get()
            } else {
                (fetchRemote() as Result.Success).value.artObjects
            }
            Result.Success(museumDataMapper.mapToDomain(result))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private suspend fun fetchRemote(): Result<MuseumDataModel> {
        return museumRemoteDatasource.loadCollection(1).also {
            if (it is Result.Success) {
                museumMemoryDatasource.set(it.value.artObjects)
            }
        }
    }

    override suspend fun getArtworkDetails(objectNumber: String): Result<ArtworkDetails> {
        return when(val result = museumRemoteDatasource.getArtworkDetails(objectNumber)) {
            is Result.Success -> Result.Success(artworkDataMapper.mapToDomain(result.value))
            is Result.Error -> Result.Error(result.throwable)
        }
    }
}