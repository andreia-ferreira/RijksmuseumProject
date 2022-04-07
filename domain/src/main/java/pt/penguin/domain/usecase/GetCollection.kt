package pt.penguin.domain.usecase

import pt.penguin.domain.model.home.MuseumCollection
import pt.penguin.domain.repository.MuseumRepository
import pt.penguin.common.Result
import pt.penguin.domain.model.home.ArtObjectDetails
import javax.inject.Inject

class GetCollection @Inject constructor(
    private val museumRepository: MuseumRepository
) {
    suspend fun execute(): Result<List<ArtObjectDetails>> {
        return museumRepository.getMuseumCollection()
    }
}