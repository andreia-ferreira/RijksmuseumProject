package pt.penguin.domain.model.details

data class ArtworkDetails(
    val objectNumber: String,
    val title: String,
    val image: String?,
    val artist: String,
    val physicalMedium: String,
    val height: String,
    val width: String,
    val weight: String,
    val plaqueDescription: String
)