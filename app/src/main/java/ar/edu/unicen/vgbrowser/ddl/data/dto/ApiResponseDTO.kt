package ar.edu.unicen.vgbrowser.ddl.data.dto

import ar.edu.unicen.vgbrowser.ddl.models.ApiResponse

data class ApiResponseDTO(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<VideogameDTO>
) {

    fun toApiResponse(): ApiResponse{
        return ApiResponse(
            count = count,
            next = next,
            previous = previous,
            results = results
        )
    }
}