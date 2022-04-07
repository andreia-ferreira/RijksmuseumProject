package pt.penguin.domain.model.details

data class ArtworkDetails(
    val objectNumber: String,
    val title: String,
    val image: String?,
    val artist: String,
    val mediumAndDimensions: List<String>,
    val plaqueDescription: String
)