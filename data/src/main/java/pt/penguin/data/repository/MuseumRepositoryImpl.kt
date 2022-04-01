package pt.penguin.data.repository

import pt.penguin.common.Result
import pt.penguin.data.datasource.MuseumRemoteDatasource
import pt.penguin.data.mapper.MuseumDataMapper
import pt.penguin.data.model.MuseumDataModel
import pt.penguin.domain.model.MuseumCollection
import pt.penguin.domain.repository.MuseumRepository
import javax.inject.Inject

class MuseumRepositoryImpl @Inject constructor(
    private val museumRemoteDatasource: MuseumRemoteDatasource,
    private val museumDataMapper: MuseumDataMapper,
): MuseumRepository {

    override suspend fun getMuseumCollection(): Result<MuseumCollection> {
        return when(val result = museumRemoteDatasource.getCollection()) {
            is Result.Success -> {
                Result.Success(museumDataMapper.mapToDomain(result.value))
            }
            is Result.Error -> Result.Error(result.throwable)
        }
    }
}