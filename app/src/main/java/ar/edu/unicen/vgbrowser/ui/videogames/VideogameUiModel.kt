package ar.edu.unicen.vgbrowser.ui.videogames

import ar.edu.unicen.vgbrowser.ddl.models.Videogame

class VideogameUiModel(
    //val id : Int,
    val name : String,
    val year : String?,
    //val genre : String,
    val description : String,
    val image : String?
) {

    companion object {
        fun Videogame.toUiModel(): VideogameUiModel {
            return VideogameUiModel(
                name = name,
                year = year,
                //genre = genre,
                description = description,
                image = image
            )
        }
    }


}