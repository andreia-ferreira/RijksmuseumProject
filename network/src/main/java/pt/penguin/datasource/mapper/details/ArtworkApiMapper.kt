package pt.penguin.datasource.mapper.details

import pt.penguin.data.model.details.ArtworkDataModel
import pt.penguin.data.model.details.DimensionTypesDataModel
import pt.penguin.datasource.common.ApiMapperException
import pt.penguin.datasource.model.details.ArtworkApiModel
import pt.penguin.datasource.model.details.DimensionApiModel
import java.lang.Exception
import javax.inject.Inject

class ArtworkApiMapper @Inject constructor() {
    fun mapToData(artworkApiModel: ArtworkApiModel?): ArtworkDataModel {
        if (artworkApiModel == null) throw ApiMapperException()
        return try {
            ArtworkDataModel(
                objectNumber = artworkApiModel.objectNumber.orEmpty(),
                title = artworkApiModel.longTitle.orEmpty(),
                image = artworkApiModel.webImage?.url.orEmpty(),
                artist = artworkApiModel.principalMaker.orEmpty(),
                physicalMedium = artworkApiModel.physicalMedium.orEmpty(),
                height = getDimension(artworkApiModel.dimensions, DimensionTypesDataModel.HEIGHT),
                width = getDimension(artworkApiModel.dimensions, DimensionTypesDataModel.WIDTH),
                weight = getDimension(artworkApiModel.dimensions, DimensionTypesDataModel.WEIGHT),
                plaqueDescription = artworkApiModel.plaqueDescriptionEnglish.orEmpty()
            )
        } catch (e: Exception) {
            throw ApiMapperException()
        }
    }

    private fun getDimension(resultList: List<DimensionApiModel>?, type: DimensionTypesDataModel)
    : String {
        val dimen = resultList?.firstOrNull { it.type == type.value }
        return "${dimen?.value} ${dimen?.unit}"
    }
}