package pt.penguin.datasource.model.home

import com.google.gson.annotations.SerializedName

data class ArtObjectWebImageApiModel(
    @SerializedName("guid")
    val guid: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("offsetPercentageX")
    val offsetPercentageX: Int?,
    @SerializedName("offsetPercentageY")
    val offsetPercentageY: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
)