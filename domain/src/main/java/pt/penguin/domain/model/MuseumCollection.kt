package pt.penguin.domain.model

data class MuseumCollection(
    val count: Int,
    val artObjects: List<ArtObjectDetails>
)