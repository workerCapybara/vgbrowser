package ar.edu.unicen.vgbrowser.ui.videogame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unicen.vgbrowser.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun VideogameDetails(
    name: String,
    year: String?,
    description: String,
    image: String?
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.secondary_color))
    ) {

        Row (
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
        ){

            Text(text = "$name ($year)",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                textAlign = TextAlign.Center
                )
        }

        GlideImage(
            modifier = Modifier
                .padding(top = 15.dp, start = 15.dp, end = 15.dp),
            model = image,
            contentDescription = null
        )

        /**
        Row {
        Text(
        text = genre
        )
        }
         */
        Row (
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp),
        ){
            Text(
                text = description
            )
        }
    }

    }




@Composable
@Preview
private fun VideogameScreenPreview(){
    VideogameDetails(
        name = "Skyrim",
        year = "2012",
        //genre = "RPG",
        description = "Juegazo",
        image = ""
    )
}