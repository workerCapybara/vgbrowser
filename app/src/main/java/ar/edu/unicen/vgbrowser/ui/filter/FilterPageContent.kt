package ar.edu.unicen.vgbrowser.ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.unicen.vgbrowser.R
import ar.edu.unicen.vgbrowser.ddl.data.dto.filterDTO.GenreDTO
import ar.edu.unicen.vgbrowser.ddl.data.dto.filterDTO.PlatformInfoDTO
import ar.edu.unicen.vgbrowser.ui.VideogameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterPageContent(
    viewModel: VideogameViewModel,
    onApplyFilters: (ordering: String?, genres: String?, platforms: String?) -> Unit,
    onBack: () -> Unit
) {
    val genres by viewModel.genres.collectAsStateWithLifecycle()
    val platforms by viewModel.platforms.collectAsStateWithLifecycle()

    var selectedOrdering by remember { mutableStateOf<String?>(null) }
    var selectedGenres by remember { mutableStateOf<Set<Int>>(emptySet()) }
    var selectedPlatforms by remember { mutableStateOf<Set<Int>>(emptySet()) }

    // Opciones de ordenamiento
    val orderingOptions = listOf(
        "Más populares" to "-rating",
        "Menos populares" to "rating",
        "Más recientes" to "-released",
        "Más antiguos" to "released",
        "A-Z" to "name",
        "Z-A" to "-name",
        "Mejor puntuados" to "-metacritic"
    )

    LaunchedEffect(Unit) {
        viewModel.getGenres()
        viewModel.getPlatforms()
    }

    Column(
        modifier = Modifier
            .background(colorResource(R.color.secondary_color))
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Filtros y Ordenamiento",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Sección de Ordenamiento
        Text(
            text = "Ordenar por",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        orderingOptions.forEach { (label, value) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = label)
                RadioButton(
                    selected = selectedOrdering == value,
                    onClick = { selectedOrdering = value }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sección de Géneros
        Text(
            text = "Géneros",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        genres?.forEach { genre ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = genre.name)
                Checkbox(
                    checked = selectedGenres.contains(genre.id),
                    onCheckedChange = { checked ->
                        selectedGenres = if (checked) {
                            selectedGenres + genre.id
                        } else {
                            selectedGenres - genre.id
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sección de Plataformas
        Text(
            text = "Plataformas",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        platforms?.forEach { platform ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = platform.name)
                Checkbox(
                    checked = selectedPlatforms.contains(platform.id),
                    onCheckedChange = { checked ->
                        selectedPlatforms = if (checked) {
                            selectedPlatforms + platform.id
                        } else {
                            selectedPlatforms - platform.id
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    selectedOrdering = null
                    selectedGenres = emptySet()
                    selectedPlatforms = emptySet()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Limpiar")
            }

            Button(
                onClick = {
                    val genresString = selectedGenres.takeIf { it.isNotEmpty() }
                        ?.joinToString(",")
                    val platformsString = selectedPlatforms.takeIf { it.isNotEmpty() }
                        ?.joinToString(",")

                    onApplyFilters(selectedOrdering, genresString, platformsString)
                    onBack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Aplicar")
            }
        }
    }
}