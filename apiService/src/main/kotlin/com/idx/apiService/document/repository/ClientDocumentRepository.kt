package com.idx.apiService.document.repository

import com.idx.apiService.document.model.ClientDocument
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
internal interface ClientDocumentRepository: ElasticsearchRepository<ClientDocument,  UUID> {
    fun findByIsActive(isActive:Boolean, pageable:Pageable): Page<ClientDocument>
}