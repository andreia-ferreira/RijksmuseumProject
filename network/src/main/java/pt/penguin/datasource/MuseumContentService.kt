package pt.penguin.datasource

import pt.penguin.datasource.model.MuseumApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MuseumContentService {
    @GET("en/collection")
    suspend fun getCollection(
        @Query("key") key: String,
        @Query("ps") resultsPerPage: Int,
        @Query("p") page: Int
    ) : Response<MuseumApiModel?>
}