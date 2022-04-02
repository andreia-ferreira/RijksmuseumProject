package pt.penguin.datasource.model.details


import com.google.gson.annotations.SerializedName

data class ArtworkApiModel(
    @SerializedName("acquisition")
    val acquisition: AcquisitionApiModel?,
    @SerializedName("dating")
    val dating: DatingApiModel?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("dimensions")
    val dimensions: List<DimensionApiModel>?,
    @SerializedName("documentation")
    val documentation: List<String>?,
    @SerializedName("exhibitions")
    val exhibitions: List<Any>?,
    @SerializedName("hasImage")
    val hasImage: Boolean?,
    @SerializedName("historicalPersons")
    val historicalPersons: List<String>?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("label")
    val label: LabelApiModel?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("longTitle")
    val longTitle: String?,
    @SerializedName("materials")
    val materials: List<String>?,
    @SerializedName("objectCollection")
    val objectCollection: List<String>?,
    @SerializedName("objectNumber")
    val objectNumber: String?,
    @SerializedName("objectTypes")
    val objectTypes: List<String>?,
    @SerializedName("physicalMedium")
    val physicalMedium: String?,
    @SerializedName("plaqueDescriptionEnglish")
    val plaqueDescriptionEnglish: String?,
    @SerializedName("principalMaker")
    val principalMaker: String?,
    @SerializedName("principalOrFirstMaker")
    val principalOrFirstMaker: String?,
    @SerializedName("productionPlaces")
    val productionPlaces: List<String>?,
    @SerializedName("showImage")
    val showImage: Boolean?,
    @SerializedName("subTitle")
    val subTitle: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("titles")
    val titles: List<String>?,
    @SerializedName("webImage")
    val webImage: WebImageApiModel?
)