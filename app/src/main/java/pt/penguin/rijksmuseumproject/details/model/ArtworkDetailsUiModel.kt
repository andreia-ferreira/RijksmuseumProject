package pt.penguin.rijksmuseumproject.details.model

import pt.penguin.rijksmuseumproject.home.model.MuseumCollectionUiModel

sealed class ArtworkDetailsUiModel {
    object Loading: ArtworkDetailsUiModel()
    object Error: ArtworkDetailsUiModel()

    data class Success(
        val objectNumber: String,
        val title: String,
        val image: String?,
        val artist: String,
        val mediumAndDimensionsDetails: String,
        val plaqueDescription: String
    ): ArtworkDetailsUiModel()
}


