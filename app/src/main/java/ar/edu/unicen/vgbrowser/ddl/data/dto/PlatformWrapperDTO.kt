package ar.edu.unicen.vgbrowser.ddl.data.dto

import com.google.gson.annotations.SerializedName

data class PlatformWrapperDTO(
    val platform: PlatformDTO,

    @SerializedName("released_at")
    val releasedAt: String?,

    val requirements: RequirementsDTO?
)