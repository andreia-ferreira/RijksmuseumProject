package pt.penguin.datasource.model

import com.google.gson.annotations.SerializedName

data class ArtObjectApiModel(
    @SerializedName("hasImage")
    val hasImage: Boolean?,
    @SerializedName("headerImage")
    val headerImage: ArtObjectHeaderImageApiModel?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("links")
    val links: ArtObjectLinksApiModel?,
    @SerializedName("longTitle")
    val longTitle: String?,
    @SerializedName("objectNumber")
    val objectNumber: String?,
    @SerializedName("permitDownload")
    val permitDownload: Boolean?,
    @SerializedName("principalOrFirstMaker")
    val principalOrFirstMaker: String?,
    @SerializedName("productionPlaces")
    val productionPlaces: List<String>?,
    @SerializedName("showImage")
    val showImage: Boolean?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("webImage")
    val webImage: ArtObjectWebImageApiModel?
)