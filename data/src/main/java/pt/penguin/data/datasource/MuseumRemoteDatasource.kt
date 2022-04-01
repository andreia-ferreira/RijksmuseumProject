package pt.penguin.data.datasource

import pt.penguin.data.model.MuseumDataModel
import pt.penguin.common.Result

interface MuseumRemoteDatasource {
    suspend fun getCollection(): Result<MuseumDataModel>
}