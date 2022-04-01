package pt.penguin.datasource.model

import com.google.gson.annotations.SerializedName

data class FacetsApiModel(
    @SerializedName("facets")
    val facets: List<Face>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("otherTerms")
    val otherTerms: Int?,
    @SerializedName("prettyName")
    val prettyName: Int?
) {
    data class Face(
        @SerializedName("key")
        val key: String?,
        @SerializedName("value")
        val value: Int?
    )
}