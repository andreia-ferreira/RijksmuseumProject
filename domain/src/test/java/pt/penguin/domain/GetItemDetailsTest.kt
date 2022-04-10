package pt.penguin.domain

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import pt.penguin.common.Result
import pt.penguin.domain.model.details.ArtworkDetails
import pt.penguin.domain.repository.MuseumRepository
import pt.penguin.domain.usecase.GetItemDetails

class GetItemDetailsTest {
    private val museumRepository: MuseumRepository = mock()
    private val getItemDetails = GetItemDetails(museumRepository)

    @Test
    fun `should return list items successfully`() = runBlocking {
        val testResult = ArtworkDetails(
            objectNumber = "123456",
            title = "Nice painting",
            image = "",
            artist = "Jane Doe",
            mediumAndDimensions = listOf("canvas", "10 cm", "10 cm", "100 g"),
            plaqueDescription = "Indeed a nice painting. Wow."
        )

        stubCollection(Result.Success(testResult))

        val result = getItemDetails.execute("")

        assert(result == Result.Success(testResult))
    }

    @Test
    fun `should return list items with error`() = runBlocking {
        val throwable = Exception("test exception")
        stubCollection(Result.Error(throwable))

        val result = getItemDetails.execute("")

        assert(result == Result.Error(throwable))
    }

    private fun stubCollection(domainModelResult: Result<ArtworkDetails>) = runBlocking {
        whenever(museumRepository.getArtworkDetails(any())).thenReturn(domainModelResult)
    }
}