package pt.penguin.datasource.model.details


import com.google.gson.annotations.SerializedName

data class ArtworkApiModel(
    @SerializedName("artObject")
    val artObject: ArtworkDetailsApiModel?,
    @SerializedName("artObjectPage")
    val artObjectPage: ArtworkPageApiModel?,
    @SerializedName("elapsedMilliseconds")
    val elapsedMilliseconds: Int?
)