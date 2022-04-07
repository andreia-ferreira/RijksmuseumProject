package pt.penguin.datasource.mapper.details

import pt.penguin.data.model.details.ArtworkDataModel
import pt.penguin.data.model.details.DimensionTypesDataModel
import pt.penguin.datasource.model.details.ArtworkApiModel
import pt.penguin.datasource.model.details.DimensionApiModel
import java.lang.Exception
import javax.inject.Inject

class ArtworkApiMapper @Inject constructor() {
    fun mapToData(artworkApiModel: ArtworkApiModel?): ArtworkDataModel {
        if (artworkApiModel?.artObject == null) throw Exception()
        return with(artworkApiModel.artObject) {
            val mediumAndDimensions = mutableListOf<String>()
            if (!physicalMedium.isNullOrEmpty()) mediumAndDimensions.add(physicalMedium)
            getDimension(dimensions, DimensionTypesDataModel.HEIGHT)?.let { mediumAndDimensions.add(it) }
            getDimension(dimensions, DimensionTypesDataModel.WIDTH)?.let { mediumAndDimensions.add(it) }
            getDimension(dimensions, DimensionTypesDataModel.WEIGHT)?.let { mediumAndDimensions.add(it) }

            try {
                ArtworkDataModel(
                    objectNumber = objectNumber.orEmpty(),
                    title = longTitle.orEmpty(),
                    image = webImage?.url.orEmpty(),
                    artist = principalMaker.orEmpty(),
                    mediumAndDimensions = mediumAndDimensions,
                    plaqueDescription = plaqueDescriptionEnglish.orEmpty()
                )
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun getDimension(resultList: List<DimensionApiModel>?, type: DimensionTypesDataModel)
    : String? {
        val dimen = resultList?.firstOrNull { it.type == type.value }
        return dimen?.let {
            "${dimen.value} ${dimen.unit}"
        }
    }
}