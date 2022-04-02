package pt.penguin.domain.usecase

import pt.penguin.domain.model.details.ArtworkDetails
import pt.penguin.domain.repository.MuseumRepository
import pt.penguin.common.Result
import javax.inject.Inject

class GetItemDetails @Inject constructor(
    private val museumRepository: MuseumRepository
) {
    suspend fun execute(objectNumber: String): Result<ArtworkDetails> {
        return museumRepository.getArtworkDetails(objectNumber)
    }
}