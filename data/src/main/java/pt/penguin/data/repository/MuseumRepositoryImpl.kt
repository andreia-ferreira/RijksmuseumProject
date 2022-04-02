package pt.penguin.data.repository

import pt.penguin.common.Result
import pt.penguin.data.datasource.MuseumRemoteDatasource
import pt.penguin.data.mapper.details.ArtworkDataMapper
import pt.penguin.data.mapper.home.MuseumDataMapper
import pt.penguin.domain.model.details.ArtworkDetails
import pt.penguin.domain.model.home.MuseumCollection
import pt.penguin.domain.repository.MuseumRepository
import javax.inject.Inject

class MuseumRepositoryImpl @Inject constructor(
    private val museumRemoteDatasource: MuseumRemoteDatasource,
    private val museumDataMapper: MuseumDataMapper,
    private val artworkDataMapper: ArtworkDataMapper,
): MuseumRepository {

    override suspend fun getMuseumCollection(): Result<MuseumCollection> {
        return when(val result = museumRemoteDatasource.getCollection()) {
            is Result.Success -> {
                Result.Success(museumDataMapper.mapToDomain(result.value))
            }
            is Result.Error -> Result.Error(result.throwable)
        }
    }

    override suspend fun getArtworkDetails(objectNumber: String): Result<ArtworkDetails> {
        return when(val result = museumRemoteDatasource.getArtworkDetails(objectNumber)) {
            is Result.Success -> Result.Success(artworkDataMapper.mapToDomain(result.value))
            is Result.Error -> Result.Error(result.throwable)
        }
    }
}