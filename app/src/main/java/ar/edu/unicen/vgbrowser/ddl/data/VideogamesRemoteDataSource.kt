package ar.edu.unicen.vgbrowser.ddl.data

import android.net.http.NetworkException
import ar.edu.unicen.vgbrowser.ddl.data.dto.VideogameDTO
import ar.edu.unicen.vgbrowser.ddl.data.dto.filterDTO.GenreDTO
import ar.edu.unicen.vgbrowser.ddl.data.dto.filterDTO.PlatformInfoDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

//Ejecuta las llamadas a la api usando Retrofit
class VideogamesRemoteDataSource @Inject constructor(
    private val videogameApi: VideogameApi,
    private val apiResponseApi: ApiResponseApi
) {

    suspend fun getVideogames(
        ordering: String? = null,
        genres: String? = null,
        platforms: String? = null
    ): List<VideogameDTO>? {

        return withContext(Dispatchers.IO) {
            try {
                val response = apiResponseApi.getVideogames(
                    ordering = ordering,
                    genres = genres,
                    platforms = platforms
                )

                val videogames = response.body()?.toApiResponse()
                val videogamesList = videogames?.getVideogamesList()

                return@withContext videogamesList
            } catch (e: IOException){
                throw Exceptions.NetworkException("No hay conexion a internet")
            }
        }
    }

    suspend fun getGenres(): List<GenreDTO>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiResponseApi.getGenres()
                return@withContext response.body()?.results
            } catch (e: IOException){
                throw Exceptions.NetworkException("No hay conexion a internet")
            }
        }
    }

    suspend fun getPlatforms(): List<PlatformInfoDTO>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiResponseApi.getPlatforms()
                return@withContext response.body()?.results
            } catch (e: IOException){
                throw Exceptions.NetworkException("No hay conexion a internet")
            }
        }
    }

}