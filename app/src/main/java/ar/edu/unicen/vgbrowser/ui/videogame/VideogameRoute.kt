package ar.edu.unicen.vgbrowser.ui.videogame

import kotlinx.serialization.Serializable

@Serializable
class VideogameRoute(
    val name: String,
    val year: String?,
    val description: String,
    val image: String?
) {
}