package pt.penguin.data.mapper

import pt.penguin.data.model.MuseumDataModel
import pt.penguin.domain.model.MuseumCollection
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