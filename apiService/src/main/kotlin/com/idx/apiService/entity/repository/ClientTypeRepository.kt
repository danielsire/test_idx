package com.idx.apiService.entity.repository

import com.idx.apiService.entity.model.ClientType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
internal interface ClientTypeRepository: JpaRepository<ClientType, Long> {

    @Query("""INSERT INTO client_type (type, created, updated) 
            VALUES(?1, now(), now()) 
            ON CONFLICT (type) 
            DO UPDATE SET type = ?1, updated = now() 
            returning id, type, created, updated""", nativeQuery = true)
    fun upsert(type: String): ClientType
}