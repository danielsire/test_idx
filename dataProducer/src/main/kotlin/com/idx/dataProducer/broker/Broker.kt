package com.idx.dataProducer.broker

import com.idx.dataProducer.dto.ClientDto
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
internal class Broker {
    @Autowired
    private val rabbitTemplate: RabbitTemplate? = null

    @Value("\${spring.rabbitmq.exchange}")
    private val exchange: String? = null

    @Value("\${spring.rabbitmq.routingkey}")
    private val routingKey: String? = null

    fun send(message: ClientDto) {
        rabbitTemplate!!.convertAndSend(exchange!!, routingKey!!, message) { m: Message ->
            m.messageProperties.headers.remove("__TypeId__")
            m
        }
    }
}