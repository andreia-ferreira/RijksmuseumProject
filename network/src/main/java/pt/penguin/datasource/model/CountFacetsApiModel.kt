package pt.penguin.datasource.model


import com.google.gson.annotations.SerializedName

data class CountFacetsApiModel(
    @SerializedName("hasimage")
    val hasimage: Int?,
    @SerializedName("ondisplay")
    val ondisplay: Int?
)