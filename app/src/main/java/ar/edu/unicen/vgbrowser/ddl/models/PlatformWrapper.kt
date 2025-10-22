package ar.edu.unicen.vgbrowser.ddl.models

import com.google.gson.annotations.SerializedName

data class PlatformWrapper(
    val platform: Platform,

    @SerializedName("released_at")
    val releasedAt: String?,

    val requirements: Requirements?
)