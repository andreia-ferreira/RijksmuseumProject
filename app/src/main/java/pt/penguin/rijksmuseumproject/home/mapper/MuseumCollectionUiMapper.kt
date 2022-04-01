package pt.penguin.rijksmuseumproject.home.mapper

import pt.penguin.domain.model.MuseumCollection
import pt.penguin.rijksmuseumproject.home.model.MuseumCollectionUiModel
import javax.inject.Inject

class MuseumCollectionUiMapper @Inject constructor() {
    fun mapToUi(museumCollection: MuseumCollection): MuseumCollectionUiModel {
        return with(museumCollection) {
            MuseumCollectionUiModel.Success(
                artObjects.map { item ->
                    MuseumCollectionUiModel.Success.ItemUiModel(
                        objectNumber = item.objectNumber,
                        title = item.title,
                        longTitle = item.longTitle,
                        author = item.author,
                        image = item.webImage.orEmpty()
                    )
                }
            )
        }
    }
}