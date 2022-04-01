package pt.penguin.datasource.mapper

import pt.penguin.data.model.ArtObjectDataModel
import pt.penguin.datasource.model.ArtObjectApiModel
import javax.inject.Inject

class ArtObjectApiMapper @Inject constructor() {
    fun mapToData(artObjectApiModel: ArtObjectApiModel): ArtObjectDataModel {
        return with(artObjectApiModel) {
            ArtObjectDataModel(
                objectNumber = objectNumber.orEmpty(),
                title = title.orEmpty(),
                author = principalOrFirstMaker.orEmpty(),
                longTitle = longTitle.orEmpty(),
                headerImage = headerImage?.url.orEmpty(),
                webImage = webImage?.url.orEmpty()
            )
        }
    }
}