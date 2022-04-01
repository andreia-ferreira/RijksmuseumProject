package pt.penguin.domain.model

data class ArtObjectDetails(
    val objectNumber: String,
    val title: String,
    val author: String,
    val longTitle: String,
    val headerImage: String?,
    val webImage: String?,
)