package pt.penguin.datasource

import pt.penguin.common.Result
import pt.penguin.data.datasource.MuseumRemoteDatasource
import pt.penguin.data.model.MuseumDataModel
import pt.penguin.datasource.mapper.MuseumApiMapper
import javax.inject.Inject
import javax.inject.Singleton

class MuseumRemoteDatasourceImpl @Inject constructor(
    private val museumContentService: MuseumContentService,
    private val museumApiMapper: MuseumApiMapper,
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
}