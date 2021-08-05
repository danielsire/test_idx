package com.idx.apiService.controller.json

import com.idx.apiService.document.model.ClientDocument
import java.util.*

data class ClientJson(
    val identifier: UUID,
    val firstName:String,
    val lastName:String,
    val email:String,
    val isActive:Boolean,
    val type: String,
    val created: Date
)

internal fun ClientDocument.toJson() =
    ClientJson(
        identifier,
        firstName,
        lastName,
        email,
        isActive,
        type,
        created
    )
