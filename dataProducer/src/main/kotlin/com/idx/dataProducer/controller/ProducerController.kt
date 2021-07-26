package com.idx.dataProducer.controller

import com.idx.dataProducer.service.ClientGeneratorService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/produce")
internal class ProducerController(
        private val clientGeneratorService: ClientGeneratorService
) {

    @GetMapping("/{qty}")
    fun produce(@PathVariable qty: Int): ResponseEntity<List<Any?>> {

        clientGeneratorService.generate(qty)
        return ResponseEntity.ok(listOf("test $qty"))
    }
}