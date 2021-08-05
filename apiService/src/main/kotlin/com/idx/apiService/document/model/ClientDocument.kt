package com.idx.apiService.document.model

import org.springframework.data.elasticsearch.annotations.Document
import java.util.*
import javax.persistence.Id

const val INDEX = "test_idx"

@Document(indexName = INDEX)
internal class ClientDocument(

        val identifier: UUID,

        val firstName:String,

        val lastName:String,

        val email:String,

        val isActive:Boolean,

        val type: String,

        val created: Date
){
        @Id
        var id: String? = null
}