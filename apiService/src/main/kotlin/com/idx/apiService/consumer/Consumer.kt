package com.idx.apiService.consumer

import com.idx.apiService.dto.ClientDto
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar
import org.springframework.stereotype.Component

@Component
internal class Consumer : RabbitListenerConfigurer {

    override fun configureRabbitListeners(rabbitListenerEndpointRegistrar: RabbitListenerEndpointRegistrar) {}

    @RabbitListener(queues = ["\${spring.rabbitmq.queue}"])
    fun receivedMessage(message: ClientDto) {
        println("--------------------")
        println(message)
    }
}