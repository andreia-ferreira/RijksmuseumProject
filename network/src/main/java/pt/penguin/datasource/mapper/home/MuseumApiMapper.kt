package pt.penguin.datasource.mapper.home

import pt.penguin.data.model.home.MuseumDataModel
import pt.penguin.datasource.model.home.MuseumApiModel
import java.lang.Exception
import javax.inject.Inject

class MuseumApiMapper @Inject constructor(
    private val artObjectApiMapper: ArtObjectApiMapper
) {
    fun mapToData(model: MuseumApiModel?): MuseumDataModel {
        if (model == null) throw Exception()
        return try {
            MuseumDataModel(
                count = model.count,
                artObjects = model.artObjects.map { artObjectApiMapper.mapToData(it) }
            )
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}