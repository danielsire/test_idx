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
    fun getByIsActive(@PathVariable isActive:Boolean): ResponseEntity<List<ClientJson>?> {
        return ResponseEntity.ok(
            clientDocumentService.getByIsActive(isActive)
                .map(ClientDocument::toJson).toList()
        )
    }

    @GetMapping("first-name/{firstName}")
    fun getByFirstNames(@PathVariable firstName:String): ResponseEntity<List<ClientJson>?> {
        clientDocumentService.findClientsByFirstName(firstName)
        return ResponseEntity.ok(
            clientDocumentService.findClientsByFirstName(firstName)
                .map(ClientDocument::toJson).toList()
        )
    }

    @GetMapping("name/{firstName}")
    fun getByFirsNameAndLastName(@PathVariable firstName:String): ResponseEntity<List<ClientJson>?> {

        return ResponseEntity.ok(
            clientDocumentService.findClientsByFirsNameAndLastName(firstName)
                .map(ClientDocument::toJson).toList()
        )
    }

    @GetMapping("names/{name}")
    fun getByName(@PathVariable name:String): ResponseEntity<List<String>?> {

        return ResponseEntity.ok(
            clientDocumentService.fetchFirstNames(name)
        )
    }

}