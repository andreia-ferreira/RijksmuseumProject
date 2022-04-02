package pt.penguin.data.model.details

data class ArtworkDataModel(
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