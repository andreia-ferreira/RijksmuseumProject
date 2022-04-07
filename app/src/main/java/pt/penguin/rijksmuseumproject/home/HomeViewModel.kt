package pt.penguin.rijksmuseumproject.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _uiState = MutableStateFlow<MuseumCollectionUiModel>(MuseumCollectionUiModel.Empty)
    val uiModel: StateFlow<MuseumCollectionUiModel> = _uiState

    init {
        loadData()
    }

    fun loadData() {
        _uiState.value = MuseumCollectionUiModel.Loading
        viewModelScope.launch {
            when(val result = getCollection.execute()) {
                is Result.Success -> _uiState.value = uiMapper.mapToUi(result.value)
                is Result.Error -> _uiState.value = MuseumCollectionUiModel.Error
            }
        }
    }
}