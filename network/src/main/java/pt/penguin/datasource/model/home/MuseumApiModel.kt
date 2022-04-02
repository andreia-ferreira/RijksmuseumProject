package pt.penguin.datasource.model.home

import com.google.gson.annotations.SerializedName

data class MuseumApiModel(
    @SerializedName("artObjects")
    val artObjects: List<ArtObjectApiModel>,
    @SerializedName("count")
    val count: Int,
    @SerializedName("countFacets")
    val countFacets: CountFacetsApiModel,
    @SerializedName("elapsedMilliseconds")
    val elapsedMilliseconds: Int,
    @SerializedName("facets")
    val facets: List<FacetsApiModel>
)