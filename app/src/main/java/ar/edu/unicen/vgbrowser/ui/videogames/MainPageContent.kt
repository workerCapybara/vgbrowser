package ar.edu.unicen.vgbrowser.ui.videogames

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ar.edu.unicen.vgbrowser.R

@Composable
fun MainPageContent(
    isLoading: Boolean,
    videogames: List<VideogameUiModel>,
    onVideogameClicked: (VideogameUiModel) -> Unit,  //Indica sobre que videojuego se hizo click
    onFilterClicked: () -> Unit
){

    Column(
        modifier = Modifier.background(colorResource(R.color.secondary_color))
    ) {
        //Barra de navegacion
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(color = colorResource(R.color.main_color))
        ) {

            //Boton de filtrado
            Button(
                modifier = Modifier.weight(0.35f)
                    .padding(top = 10.dp, start = 10.dp),
                onClick = {onFilterClicked()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.secondary_color)
                )
            ) {
                Text(text = stringResource(R.string.filter_button),
                    textAlign = TextAlign.Center)
            }

            //Logo de la pagina
            Image(
                painter = painterResource(id = R.drawable.vglogo),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.weight(0.35f)
                    .padding(top = 10.dp, bottom = 10.dp)
                    .height(60.dp)
                    .width(140.dp)
            )

            //Input
            TextField(
                modifier = Modifier.weight(0.3f)
                    .padding(top = 10.dp, end = 10.dp),
                state = rememberTextFieldState(initialText = ""),
                label = { Text(text = stringResource(id = R.string.search_bar)) }
            )
        }

        //Lista de videojuegos
        Row (
            modifier = Modifier.padding(start = 15.dp, end = 15.dp)
        ){
            if(isLoading){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Text(text = stringResource(id = R.string.loading_text))
                }

            }else{
                LazyColumn(
                    modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(videogames){videogame ->

                        VideogameItem(
                            name = videogame.name,
                            year = videogame.year,
                            //genre = videogame.genre,
                            description = videogame.description,
                            image = videogame.image,
                            onClick = {
                                onVideogameClicked(videogame)
                            }
                        )

                    }
                }
            }
        }
    }




}