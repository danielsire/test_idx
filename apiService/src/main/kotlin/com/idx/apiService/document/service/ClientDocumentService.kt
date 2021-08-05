package com.idx.apiService.document.service

import com.idx.apiService.document.repository.ClientDocumentRepository
import com.idx.apiService.dto.ClientDto
import com.idx.apiService.dto.toDocument
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
internal class ClientDocumentService(
        private val clientDocumentRepository: ClientDocumentRepository
) {
    fun save(client: ClientDto) {
        clientDocumentRepository.save(client.toDocument())
    }

    fun getByIsActive(isActive:Boolean) =
        clientDocumentRepository.findByIsActive(isActive, PageRequest.of(0, 30)).asSequence()

}
