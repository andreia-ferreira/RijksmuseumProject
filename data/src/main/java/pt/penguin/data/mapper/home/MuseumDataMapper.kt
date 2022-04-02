package pt.penguin.data.mapper.home

import pt.penguin.data.model.home.MuseumDataModel
import pt.penguin.domain.model.home.MuseumCollection
import javax.inject.Inject

class MuseumDataMapper @Inject constructor(
    private val artObjectDataMapper: ArtObjectDataMapper
) {
    fun mapToDomain(model: MuseumDataModel): MuseumCollection {
        return MuseumCollection(
            count = model.count,
            artObjects = model.artObjects.map { artObjectDataMapper.mapToDomain(it) }
        )
    }
}