package pt.penguin.datasource.model.details


import com.google.gson.annotations.SerializedName

data class ArtworkDetailsApiModel(
    @SerializedName("artObject")
    val artObject: ArtworkApiModel?,
    @SerializedName("artObjectPage")
    val artObjectPage: ArtworkPageApiModel?,
    @SerializedName("elapsedMilliseconds")
    val elapsedMilliseconds: Int?
)