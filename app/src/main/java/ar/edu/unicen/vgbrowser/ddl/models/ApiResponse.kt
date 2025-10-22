package ar.edu.unicen.vgbrowser.ddl.models

import android.provider.MediaStore
import ar.edu.unicen.vgbrowser.ddl.data.dto.VideogameDTO

class ApiResponse(
    val count : Int,
    val next : String?,
    val previous : String?,
    val results : List<VideogameDTO>
) {

    fun getVideogamesList(): List<VideogameDTO>{
        return results
    }
}