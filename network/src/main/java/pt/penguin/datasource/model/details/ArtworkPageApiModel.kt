package pt.penguin.datasource.model.details


import com.google.gson.annotations.SerializedName

data class ArtworkPageApiModel(
    @SerializedName("id")
    val id: String?,
    @SerializedName("lang")
    val lang: String?,
    @SerializedName("objectNumber")
    val objectNumber: String?,
    @SerializedName("plaqueDescription")
    val plaqueDescription: String?,
)