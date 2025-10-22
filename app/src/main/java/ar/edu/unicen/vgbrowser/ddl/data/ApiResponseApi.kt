package ar.edu.unicen.vgbrowser.ddl.data

import ar.edu.unicen.vgbrowser.ddl.data.dto.ApiResponseDTO
import ar.edu.unicen.vgbrowser.ddl.data.dto.filterDTO.GenresResponseDTO
import ar.edu.unicen.vgbrowser.ddl.data.dto.filterDTO.PlatformsResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//Esta interface define los endpoints de la api
interface ApiResponseApi {

    @GET("games")
    suspend fun getVideogames(
        @Query("ordering") ordering: String? = null,
        @Query("genres") genres: String? = null,
        @Query("platforms") platforms: String? = null,
        @Query("page_size") pageSize: Int? = 40
    ): Response<ApiResponseDTO>

    @GET("platforms")
    suspend fun getPlatforms(): Response<PlatformsResponseDTO>

    @GET("genres")
    suspend fun getGenres(): Response<GenresResponseDTO>
}