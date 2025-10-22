package ar.edu.unicen.vgbrowser.ddl.data.dto

import ar.edu.unicen.vgbrowser.ddl.models.ApiResponse
import ar.edu.unicen.vgbrowser.ddl.models.Videogame
import com.google.gson.annotations.SerializedName


data class VideogameDTO(
val id: Int,
val slug: String,
val name: String,
val released: String?,
val tba: Boolean,

@SerializedName("background_image")
val backgroundImage: String?,

val rating: Double,

@SerializedName("rating_top")
val ratingTop: Int,

val ratings: List<Any>?,

@SerializedName("ratings_count")
val ratingsCount: Int,

@SerializedName("reviews_text_count")
val reviewsTextCount: String?,

val added: Int,

@SerializedName("added_by_status")
val addedByStatus: Map<String, Any>?,

val metacritic: Int,
val playtime: Int,

@SerializedName("suggestions_count")
val suggestionsCount: Int,

val updated: String,

@SerializedName("esrb_rating")
val esrbRating: EsrbRatingDTO?,

val platforms: List<PlatformWrapperDTO>?
)
{
    fun toVideogame(): Videogame{
        return Videogame(
            id = id,
            name = name,
            year = released,
            //genre = ,
            description = slug,
            image = backgroundImage
            //info
        )
    }

}


