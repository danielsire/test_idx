package com.idx.apiService.entity.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity(name = "client_type")
internal class ClientType(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        val type: String
){
        @field:CreationTimestamp
        lateinit var created: Date

        @field:UpdateTimestamp
        lateinit var updated: Date
}