package pt.penguin.datasource.model.details


import com.google.gson.annotations.SerializedName

data class LabelApiModel(
    @SerializedName("date")
    val date: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("makerLine")
    val makerLine: String?,
    @SerializedName("title")
    val title: String?
)