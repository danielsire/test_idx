package com.idx.apiService.consumer

import com.idx.apiService.document.service.ClientDocumentService
import com.idx.apiService.dto.ClientDto
import com.idx.apiService.entity.service.ClientService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
internal class Consumer(
        private val clientDocumentService: ClientDocumentService,
        private val clientService: ClientService
) : RabbitListenerConfigurer {

    override fun configureRabbitListeners(rabbitListenerEndpointRegistrar: RabbitListenerEndpointRegistrar) {}

    @Transactional
    @RabbitListener(queues = ["\${spring.rabbitmq.queue}"])
    fun receivedMessage(message: ClientDto) {
        println("--------------------")
        println(message)
        clientDocumentService.save(message)
        clientService.save(message)
    }
}