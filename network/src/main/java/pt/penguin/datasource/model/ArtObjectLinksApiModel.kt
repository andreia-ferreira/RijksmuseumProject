package pt.penguin.datasource.model


import com.google.gson.annotations.SerializedName

data class ArtObjectLinksApiModel(
    @SerializedName("self")
    val self: String?,
    @SerializedName("web")
    val web: String?
)