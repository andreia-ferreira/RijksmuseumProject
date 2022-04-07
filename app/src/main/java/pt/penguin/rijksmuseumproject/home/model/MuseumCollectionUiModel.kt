package pt.penguin.rijksmuseumproject.home.model

sealed class MuseumCollectionUiModel {
    object Empty: MuseumCollectionUiModel()
    object Loading: MuseumCollectionUiModel()
    object Error: MuseumCollectionUiModel()

    data class Success(
        val itemList: List<ItemUiModel>
    ): MuseumCollectionUiModel() {
        data class ItemUiModel(
            val objectNumber: String,
            val title: String,
            val longTitle: String,
            val image: String,
            val author: String
        )
    }
}