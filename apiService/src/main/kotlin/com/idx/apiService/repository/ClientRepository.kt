package com.idx.apiService.repository

import com.idx.apiService.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface ClientRepository: JpaRepository<Client, Long>