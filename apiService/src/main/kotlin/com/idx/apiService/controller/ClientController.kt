package com.idx.apiService.controller

import com.idx.apiService.controller.json.ClientJson
import com.idx.apiService.controller.json.toJson
import com.idx.apiService.document.model.ClientDocument
import com.idx.apiService.document.service.ClientDocumentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/client")
internal class ClientController(
    private val clientDocumentService: ClientDocumentService
) {

    @GetMapping("/{isActive}")
    fun produce(@PathVariable isActive:Boolean): ResponseEntity<List<ClientJson?>> {
        return ResponseEntity.ok(
            clientDocumentService.getByIsActive(isActive).map(ClientDocument::toJson).toList()
        )
    }

}