package ar.edu.unicen.vgbrowser.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unicen.vgbrowser.ddl.data.Exceptions
import ar.edu.unicen.vgbrowser.ddl.data.VideogamesRepository
import ar.edu.unicen.vgbrowser.ddl.data.dto.filterDTO.GenreDTO
import ar.edu.unicen.vgbrowser.ddl.data.dto.filterDTO.PlatformInfoDTO
import ar.edu.unicen.vgbrowser.ddl.models.Videogame
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideogameViewModel @Inject constructor(
    private val videogamesRepository: VideogamesRepository
): ViewModel(){

    private val _loading = MutableStateFlow(false)
    val loading=_loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _videogame = MutableStateFlow<List<Videogame>?>(null)
    val videogame=_videogame.asStateFlow()

    private val _genres = MutableStateFlow<List<GenreDTO>?>(null)
    val genres = _genres.asStateFlow()

    private val _platforms = MutableStateFlow<List<PlatformInfoDTO>?>(null)
    val platforms = _platforms.asStateFlow()

    private var _currentOrdering = MutableStateFlow<String?>(null)
    val currentOrdering = _currentOrdering.asStateFlow()

    private var _currentGenres = MutableStateFlow<String?>(null)
    val currentGenres = _currentGenres.asStateFlow()

    private var _currentPlatforms = MutableStateFlow<String?>(null)
    val currentPlatforms = _currentPlatforms.asStateFlow()


    fun getVideogames(
        ordering: String? = null,
        genres: String? = null,
        platforms: String? = null
    ) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            _videogame.value = emptyList()

            _currentOrdering.value = ordering
            _currentGenres.value = genres
            _currentPlatforms.value = platforms

            try {
                val videogame = videogamesRepository.getVideogames(
                    ordering = ordering,
                    genres = genres,
                    platforms = platforms
                )

                _videogame.value = videogame
                _error.value = null
            } catch (e: Exceptions.NetworkException) {
                _error.value = "Sin conexión a internet. Verifica tu red."
                _videogame.value = null
            } catch (e: Exception) {
                _error.value = "Error al cargar los videojuegos"
                _videogame.value = null
            }

            _loading.value = false
        }
    }

    fun reloadVideogames() {
        getVideogames(currentOrdering.value, currentGenres.value, currentPlatforms.value)
    }

    fun getGenres() {
        viewModelScope.launch {
            try {
                val genresList = videogamesRepository.getGenres()
                _genres.value = genresList
            } catch (e: Exceptions.NetworkException) {
                _error.value = "Sin conexión a internet. No se pudieron cargar los géneros."
            } catch (e: Exception) {
                _error.value = "Error al cargar los géneros"
            }
        }
    }

    fun getPlatforms() {
        viewModelScope.launch {
            try {
                val platformsList = videogamesRepository.getPlatforms()
                _platforms.value = platformsList
            } catch (e: Exceptions.NetworkException) {
                _error.value = "Sin conexión a internet. No se pudieron cargar las plataformas."
            } catch (e: Exception) {
                _error.value = "Error al cargar las plataformas"
            }
        }
    }

    fun clearFilters() {
        getVideogames(null, null, null)
    }

}