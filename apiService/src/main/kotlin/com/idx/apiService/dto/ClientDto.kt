package com.idx.apiService.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.idx.apiService.document.model.ClientDocument
import com.idx.apiService.entity.model.Client
import com.idx.apiService.entity.model.ClientType
import java.util.*

internal data class ClientDto(
        @JsonProperty("identifier") val identifier: UUID,
        @JsonProperty("firstName") val firstName:String,
        @JsonProperty("lastName") val lastName:String,
        @JsonProperty("email") val email:String,
        @JsonProperty("active") val isActive:Boolean,
        @JsonProperty("type") val type: String
)

internal fun ClientDto.toDocument() =
        ClientDocument(
                identifier,
                firstName,
                lastName,
                email,
                isActive,
                type,
                created = Date()
        )

internal fun ClientDto.toEntity(clientType: ClientType) =
        Client(
                identifier = identifier,
                firstName = firstName,
                lastName = lastName,
                email = email,
                isActive = isActive,
                type = clientType,
        )