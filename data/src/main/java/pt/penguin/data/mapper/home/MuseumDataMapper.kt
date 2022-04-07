package pt.penguin.data.mapper.home

import pt.penguin.data.model.home.ArtObjectDataModel
import pt.penguin.data.model.home.MuseumDataModel
import pt.penguin.domain.model.home.ArtObjectDetails
import pt.penguin.domain.model.home.MuseumCollection
import java.lang.Exception
import javax.inject.Inject

class MuseumDataMapper @Inject constructor() {
    fun mapToDomain(listObjects: List<ArtObjectDataModel>?)
    : List<ArtObjectDetails> {
        return try {
            listObjects?.map {
                with(it) {
                    ArtObjectDetails(
                        objectNumber = objectNumber,
                        title = title,
                        author = author,
                        longTitle = longTitle,
                        headerImage = headerImage,
                        webImage = webImage
                    )
                }
            } ?: throw Exception()
        } catch (e: Exception) {
            throw e
        }
    }
}