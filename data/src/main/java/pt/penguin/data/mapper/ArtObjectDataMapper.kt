package pt.penguin.data.mapper

import pt.penguin.data.model.ArtObjectDataModel
import pt.penguin.domain.model.ArtObjectDetails
import javax.inject.Inject

class ArtObjectDataMapper @Inject constructor() {
    fun mapToDomain(artObjectDataModel: ArtObjectDataModel): ArtObjectDetails {
        return with(artObjectDataModel) {
            ArtObjectDetails(
                objectNumber = objectNumber,
                title = title,
                author = author,
                longTitle = longTitle,
                headerImage = headerImage,
                webImage = webImage
            )
        }
    }
}