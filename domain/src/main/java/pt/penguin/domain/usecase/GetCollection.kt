package pt.penguin.domain.usecase

import pt.penguin.domain.model.home.MuseumCollection
import pt.penguin.domain.repository.MuseumRepository
import pt.penguin.common.Result
import javax.inject.Inject

class GetCollection @Inject constructor(
    private val museumRepository: MuseumRepository
) {
    suspend fun execute(): Result<MuseumCollection> {
        return museumRepository.getMuseumCollection()
    }
}