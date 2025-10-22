package ar.edu.unicen.vgbrowser.ui.videogames

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.unicen.vgbrowser.ui.videogames.VideogameUiModel.Companion.toUiModel
import ar.edu.unicen.vgbrowser.ui.VideogameViewModel

@Composable
fun VideogamesScreen(
    viewModel: VideogameViewModel,
    goDetails: (VideogameUiModel) -> Unit,
    goFilters: () -> Unit
) {
    val isLoading by viewModel.loading.collectAsStateWithLifecycle()
    val videogames by viewModel.videogame.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val currentOrdering by viewModel.currentOrdering.collectAsStateWithLifecycle()
    val currentGenres by viewModel.currentGenres.collectAsStateWithLifecycle()
    val currentPlatforms by viewModel.currentPlatforms.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (videogames == null && error == null) {
            viewModel.getVideogames()
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(error) {
        error?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Long
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when {
                error != null && videogames == null -> {
                    ErrorScreen(
                        message = error!!,
                        onRetry = { viewModel.getVideogames() }
                    )
                }
                else -> {
                    MainPageContent(
                        isLoading = isLoading,
                        videogames = videogames?.map { it.toUiModel() }.orEmpty(),
                        onVideogameClicked = goDetails,
                        onFilterClicked = goFilters
                    )
                }
            }
        }
    }
}


@Composable
fun ErrorScreen(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "⚠️",
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onRetry) {
            Text("Reintentar")
        }
    }
}