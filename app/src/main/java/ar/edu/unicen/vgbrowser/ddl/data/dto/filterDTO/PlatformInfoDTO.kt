package ar.edu.unicen.vgbrowser.ddl.data.dto.filterDTO

import com.google.gson.annotations.SerializedName

data class PlatformInfoDTO(
    val id: Int,
    val name: String,
    val slug: String,
    @SerializedName("games_count")
    val gamesCount: Int?,
    @SerializedName("image_background")
    val imageBackground: String?
)