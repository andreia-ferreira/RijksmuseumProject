package pt.penguin.data.model.details

data class ArtworkDataModel(
    val objectNumber: String,
    val title: String,
    val image: String?,
    val artist: String,
    val mediumAndDimensions: List<String>,
    val plaqueDescription: String
)