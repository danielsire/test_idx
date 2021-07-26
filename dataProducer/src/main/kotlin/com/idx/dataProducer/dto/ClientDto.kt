package com.idx.dataProducer.dto

import java.util.*

data class ClientDto(
        val identifier: UUID,
        val firstName:String,
        val lastName:String,
        val email:String,
        val isActive:Boolean,
        val type: String
)