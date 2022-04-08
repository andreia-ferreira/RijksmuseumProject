package pt.penguin.data.repository

import pt.penguin.common.Result
import pt.penguin.data.datasource.MuseumMemoryDataSource
import pt.penguin.data.datasource.MuseumRemoteDatasource
import pt.penguin.data.mapper.details.ArtworkDataMapper
import pt.penguin.data.mapper.home.MuseumDataMapper
import pt.penguin.data.model.home.MuseumDataModel
import pt.penguin.domain.model.details.ArtworkDetails
import pt.penguin.domain.model.home.ArtObjectDetails
import pt.penguin.domain.repository.MuseumRepository
import java.lang.Exception
import javax.inject.Inject

class MuseumRepositoryImpl @Inject constructor(
    private val museumRemoteDatasource: MuseumRemoteDatasource,
    private val museumMemoryDatasource: MuseumMemoryDataSource,
    private val museumDataMapper: MuseumDataMapper,
    private val artworkDataMapper: ArtworkDataMapper,
): MuseumRepository {

    private var nextPage = 1

    override suspend fun getMuseumCollection(): Result<List<ArtObjectDetails>> {
        return try {
            fetchRemote()
            val result = museumMemoryDatasource.get()
            Result.Success(museumDataMapper.mapToDomain(result))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private suspend fun fetchRemote(): Result<MuseumDataModel> {
        return museumRemoteDatasource.loadCollection(nextPage).also {
            if (it is Result.Success) {
                val cache = museumMemoryDatasource.get().orEmpty().toMutableList()
                cache.addAll(it.value.artObjects)
                museumMemoryDatasource.set(cache)
                nextPage ++
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