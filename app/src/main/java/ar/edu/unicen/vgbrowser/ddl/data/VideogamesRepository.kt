package ar.edu.unicen.vgbrowser.ddl.data

import ar.edu.unicen.vgbrowser.ddl.data.dto.VideogameDTO
import ar.edu.unicen.vgbrowser.ddl.data.dto.filterDTO.GenreDTO
import ar.edu.unicen.vgbrowser.ddl.data.dto.filterDTO.PlatformInfoDTO
import ar.edu.unicen.vgbrowser.ddl.models.ApiResponse
import ar.edu.unicen.vgbrowser.ddl.models.Videogame
import javax.inject.Inject

class VideogamesRepository @Inject constructor (private val remoteDataSource: VideogamesRemoteDataSource) {

    suspend fun getVideogames(
        ordering: String? = null,
        genres: String? = null,
        platforms: String? = null
    ): List<Videogame>? {
        val videogamesDTO = remoteDataSource.getVideogames(
            ordering = ordering,
            genres = genres,
            platforms = platforms
        )
        return videogamesDTO?.map { it.toVideogame() }
    }

    suspend fun getGenres(): List<GenreDTO>? {
        return remoteDataSource.getGenres()
    }

    suspend fun getPlatforms(): List<PlatformInfoDTO>? {
        return remoteDataSource.getPlatforms()
    }



}