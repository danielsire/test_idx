package com.idx.apiService.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class ClientDto(
        @JsonProperty("identifier") val identifier: UUID,
        @JsonProperty("firstName") val firstName:String,
        @JsonProperty("lastName") val lastName:String,
        @JsonProperty("email") val email:String,
        @JsonProperty("isActive") val isActive:Boolean,
        @JsonProperty("type") val type: String
)