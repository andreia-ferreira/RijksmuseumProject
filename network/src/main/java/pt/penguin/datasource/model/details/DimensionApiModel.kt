package pt.penguin.datasource.model.details


import com.google.gson.annotations.SerializedName

data class DimensionApiModel(
    @SerializedName("type")
    val type: String?,
    @SerializedName("unit")
    val unit: String?,
    @SerializedName("value")
    val value: String?
)