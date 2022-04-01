package pt.penguin.datasource.mapper

import pt.penguin.data.model.MuseumDataModel
import pt.penguin.datasource.model.MuseumApiModel
import pt.penguin.datasource.common.ApiMapperException
import java.lang.Exception
import javax.inject.Inject

class MuseumApiMapper @Inject constructor(
    private val artObjectApiMapper: ArtObjectApiMapper
) {
    fun mapToData(model: MuseumApiModel?): MuseumDataModel {
        if (model == null) throw ApiMapperException()
        return try {
            MuseumDataModel(
                count = model.count,
                artObjects = model.artObjects.map { artObjectApiMapper.mapToData(it) }
            )
        } catch (e: Exception) {
            throw ApiMapperException()
        }
    }
}