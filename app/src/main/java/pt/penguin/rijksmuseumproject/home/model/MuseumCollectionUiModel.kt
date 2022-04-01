package pt.penguin.rijksmuseumproject.home.model

sealed class MuseumCollectionUiModel {

    object Loading: MuseumCollectionUiModel()

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

    data class Error(
        val message: String
    ): MuseumCollectionUiModel()
}