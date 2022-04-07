package pt.penguin.rijksmuseumproject.details.mapper

import pt.penguin.domain.model.details.ArtworkDetails
import pt.penguin.rijksmuseumproject.details.model.ArtworkDetailsUiModel
import javax.inject.Inject

class ArtworkDetailsUiModelMapper @Inject constructor() {
    fun mapToUi(
        artworkDetails: ArtworkDetails
    ): ArtworkDetailsUiModel {
        return with(artworkDetails) {
            ArtworkDetailsUiModel.Success(
                objectNumber = objectNumber,
                title = title,
                image = image,
                artist = artist,
                mediumAndDimensionsDetails = mediumAndDimensions.joinToString( " | ") ,
                plaqueDescription = plaqueDescription
            )
        }
    }
}