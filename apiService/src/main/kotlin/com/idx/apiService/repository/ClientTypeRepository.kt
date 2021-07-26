package com.idx.apiService.repository

import com.idx.apiService.model.ClientType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface ClientTypeRepository: JpaRepository<ClientType, Long>