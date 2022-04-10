package pt.penguin.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import pt.penguin.common.Result
import pt.penguin.domain.model.home.ArtObjectDetails
import pt.penguin.domain.repository.MuseumRepository
import pt.penguin.domain.usecase.GetCollection

class GetCollectionTest {
    private val museumRepository: MuseumRepository = mock()
    private val getCollection = GetCollection(museumRepository)

    @Test
    fun `should return list items successfully`() = runBlocking {
        val testResult = listOf(ArtObjectDetails(
            objectNumber = "123456",
            title = "Nice painting",
            longTitle = "Nice painting, Jane Doe, 2022",
            headerImage = "",
            webImage = "",
            author = "Jane Doe"
        ))
        stubCollection(Result.Success(testResult))

        val result = getCollection.execute()

        assert(result == Result.Success(testResult))
    }

    @Test
    fun `should return list items with error`() = runBlocking {
        val throwable = Exception("test exception")
        stubCollection(Result.Error(throwable))

        val result = getCollection.execute()

        assert(result == Result.Error(throwable))
    }

    private fun stubCollection(domainModelResult: Result<List<ArtObjectDetails>>) = runBlocking {
        whenever(museumRepository.getMuseumCollection()).thenReturn(domainModelResult)
    }
}