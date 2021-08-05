package com.idx.apiService.entity.service

import com.idx.apiService.dto.ClientDto
import com.idx.apiService.dto.toEntity
import com.idx.apiService.entity.repository.ClientRepository
import com.idx.apiService.entity.repository.ClientTypeRepository
import org.hibernate.exception.ConstraintViolationException
import org.springframework.stereotype.Service
import mu.KotlinLogging
import org.springframework.dao.DataIntegrityViolationException


@Service
internal class ClientService(
        private val clientRepository: ClientRepository,
        private val clientTypeRepository: ClientTypeRepository
) {

    fun save(client: ClientDto) {
        val clientType = clientTypeRepository.upsert(client.type)

        try {
            clientRepository.save(client.toEntity(clientType))
        } catch (ex: DataIntegrityViolationException) {
            logger.info { ex.cause }
        }


    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }

}