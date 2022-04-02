package pt.penguin.data.mapper.details

import pt.penguin.data.model.details.ArtworkDataModel
import pt.penguin.domain.model.details.ArtworkDetails
import javax.inject.Inject

class ArtworkDataMapper @Inject constructor() {
    fun mapToDomain(dataModel: ArtworkDataModel): ArtworkDetails {
        return with(dataModel) {
            ArtworkDetails(
                objectNumber = objectNumber,
                title = title,
                image = image,
                artist = artist,
                physicalMedium = physicalMedium,
                height = height,
                width = width,
                weight = weight,
                plaqueDescription = plaqueDescription
            )
        }
    }
}