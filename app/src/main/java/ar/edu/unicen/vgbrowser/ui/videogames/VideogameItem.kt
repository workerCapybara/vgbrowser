package ar.edu.unicen.vgbrowser.ui.videogames;

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unicen.vgbrowser.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun VideogameItem(
    modifier: Modifier = Modifier,
    //id : Int,
    name: String,
    year: String?,
    //genre: String,
    description: String,
    image: String?,
    onClick: () -> Unit
){
        Column(
            modifier = Modifier
                .clickable{ onClick() }
        ) {

            GlideImage(
                model = image,
                contentDescription = null
            )

            Spacer(Modifier.padding(top = 5.dp))

            Row {
                Text(text = "$name (${stringResource(R.string.release_date)} $year)")
            }

/**
            Row {
                Text(
                    text = genre
                )
            }

            Row {
                Text(
                    text = description
                )
            }
 */
        }


}

@Composable
@Preview
private fun VideogamePreview(){
    VideogameItem(
        name = "Skyrim",
        year = "2012",
        //genre = "RPG",
        description = "Juegazo",
        image = "",
        onClick = {}
    )
}