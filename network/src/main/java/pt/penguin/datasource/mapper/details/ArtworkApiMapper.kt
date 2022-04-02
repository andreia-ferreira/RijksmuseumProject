package pt.penguin.datasource.mapper.details

import pt.penguin.data.model.details.ArtworkDataModel
import pt.penguin.data.model.details.DimensionTypesDataModel
import pt.penguin.datasource.common.ApiMapperException
import pt.penguin.datasource.model.details.ArtworkApiModel
import pt.penguin.datasource.model.details.ArtworkDetailsApiModel
import pt.penguin.datasource.model.details.DimensionApiModel
import java.lang.Exception
import javax.inject.Inject

class ArtworkApiMapper @Inject constructor() {
    fun mapToData(artworkApiModel: ArtworkApiModel?): ArtworkDataModel {
        if (artworkApiModel?.artObject == null) throw ApiMapperException()
        return with(artworkApiModel.artObject) {
            try {
                ArtworkDataModel(
                    objectNumber = objectNumber.orEmpty(),
                    title = longTitle.orEmpty(),
                    image = webImage?.url.orEmpty(),
                    artist = principalMaker.orEmpty(),
                    physicalMedium = physicalMedium.orEmpty(),
                    height = getDimension(dimensions, DimensionTypesDataModel.HEIGHT),
                    width = getDimension(dimensions, DimensionTypesDataModel.WIDTH),
                    weight = getDimension(dimensions, DimensionTypesDataModel.WEIGHT),
                    plaqueDescription = plaqueDescriptionEnglish.orEmpty()
                )
            } catch (e: Exception) {
                throw ApiMapperException()
            }
        }
    }

    private fun getDimension(resultList: List<DimensionApiModel>?, type: DimensionTypesDataModel)
    : String {
        val dimen = resultList?.firstOrNull { it.type == type.value }
        return dimen?.let {
            "${dimen.value} ${dimen.unit}"
        } ?: ""
    }
}