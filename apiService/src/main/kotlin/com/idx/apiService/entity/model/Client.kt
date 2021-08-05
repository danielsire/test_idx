package com.idx.apiService.entity.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
internal class Client(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        val identifier: UUID,

        val firstName:String,

        val lastName:String,

        val email:String,

        val isActive:Boolean,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name= "client_type_id")
        val type: ClientType
) {
        @field:CreationTimestamp
        lateinit var created: Date

        @field:UpdateTimestamp
        lateinit var updated: Date
}