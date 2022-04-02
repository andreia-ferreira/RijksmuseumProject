package pt.penguin.rijksmuseumproject.home

import SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pt.penguin.common.Result
import pt.penguin.domain.usecase.GetCollection
import pt.penguin.rijksmuseumproject.home.mapper.MuseumCollectionUiMapper
import pt.penguin.rijksmuseumproject.home.model.MuseumCollectionUiModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCollection: GetCollection,
    private val uiMapper: MuseumCollectionUiMapper
): ViewModel() {

    private val _uiModel: MutableLiveData<MuseumCollectionUiModel> = MutableLiveData()
    val uiModel: LiveData<MuseumCollectionUiModel> = _uiModel
    private val _openItemDetails: MutableLiveData<String> = SingleLiveEvent()
    val openItemDetails: LiveData<String> = _openItemDetails

    fun init() {
        _uiModel.postValue(MuseumCollectionUiModel.Loading)
        viewModelScope.launch {
            when(val result = getCollection.execute()) {
                is Result.Success -> _uiModel.postValue(
                    uiMapper.mapToUi(result.value) { number -> onClickItemAction(number) }
                )
                is Result.Error -> _uiModel.postValue(
                    MuseumCollectionUiModel.Error(result.throwable.message.orEmpty())
                )
            }
        }
    }

    private fun onClickItemAction(orderNumber: String) {
        _openItemDetails.postValue(orderNumber)
    }

}