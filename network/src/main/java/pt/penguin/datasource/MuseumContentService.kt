package pt.penguin.datasource

import pt.penguin.datasource.model.details.ArtworkApiModel
import pt.penguin.datasource.model.details.ArtworkDetailsApiModel
import pt.penguin.datasource.model.home.MuseumApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MuseumContentService {
    @GET("en/collection")
    suspend fun getCollection(
        @Query("key") key: String,
        @Query("ps") resultsPerPage: Int,
        @Query("p") page: Int
    ) : Response<MuseumApiModel?>

    @GET("en/collection/{object_number}")
    suspend fun getArtworkDetails(
        @Path(value = "object_number") objectNumber: String,
        @Query("key") key: String
    ) : Response<ArtworkApiModel?>
}