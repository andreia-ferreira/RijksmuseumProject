package pt.penguin.domain.model.home

data class MuseumCollection(
    val count: Int,
    val artObjects: List<ArtObjectDetails>
)