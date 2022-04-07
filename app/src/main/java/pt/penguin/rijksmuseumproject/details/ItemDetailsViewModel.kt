package pt.penguin.rijksmuseumproject.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pt.penguin.common.Result
import pt.penguin.domain.usecase.GetItemDetails
import pt.penguin.rijksmuseumproject.details.mapper.ArtworkDetailsUiModelMapper
import pt.penguin.rijksmuseumproject.details.model.ArtworkDetailsUiModel
import javax.inject.Inject

@HiltViewModel
class ItemDetailsViewModel @Inject constructor(
    private val getItemDetails: GetItemDetails,
    private val artworkDetailsUiModelMapper: ArtworkDetailsUiModelMapper
): ViewModel() {

    private val _uiModel: MutableLiveData<ArtworkDetailsUiModel> = MutableLiveData()
    val uiModel: LiveData<ArtworkDetailsUiModel> = _uiModel

    fun init(objectNumber: String) {
        _uiModel.postValue(ArtworkDetailsUiModel.Loading)
        viewModelScope.launch {
            when(val result = getItemDetails.execute(objectNumber)) {
                is Result.Success -> _uiModel.postValue(
                    artworkDetailsUiModelMapper.mapToUi(result.value)
                )
                is Result.Error -> _uiModel.postValue(
                    ArtworkDetailsUiModel.Error
                )
            }
        }
    }

}