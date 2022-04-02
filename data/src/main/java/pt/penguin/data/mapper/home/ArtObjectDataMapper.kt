package pt.penguin.data.mapper.home

import pt.penguin.data.model.home.ArtObjectDataModel
import pt.penguin.domain.model.home.ArtObjectDetails
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