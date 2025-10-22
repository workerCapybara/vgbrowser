package ar.edu.unicen.vgbrowser.ddl.data.dto.filterDTO

data class GenreDTO(
    val id: Int,
    val name: String,
    val slug: String,
    val games_count: Int?,
    val image_background: String?
)