package com.idx.apiService

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.web.WebApplicationInitializer

@SpringBootApplication
class ApiServiceApplication: SpringBootServletInitializer(), WebApplicationInitializer {
	@Value("\${spring.rabbitmq.host}")
	private lateinit var host: String

	@Value("\${spring.rabbitmq.username}")
	private lateinit var username: String

	@Value("\${spring.rabbitmq.password}")
	private lateinit var password: String

	@Value("\${spring.rabbitmq.exchange}")
	private lateinit var exchange: String

	@Value("\${spring.rabbitmq.routingkey}")
	private lateinit var routingKey: String

	@Value("\${spring.rabbitmq.queue}")
	private lateinit var queue: String

	@Bean
	fun queue(): Queue? = Queue(queue, true)

	@Bean
	fun exchange(): Exchange? = ExchangeBuilder.directExchange(exchange).durable(true).build()

	@Bean
	fun binding(): Binding = BindingBuilder.bind(queue()).to(exchange()).with(routingKey).noargs()

	@Bean
	fun connectionFactory(): CachingConnectionFactory? {
		val cachingConnectionFactory = CachingConnectionFactory(host)
		cachingConnectionFactory.username = username
		cachingConnectionFactory.setPassword(password)
		return cachingConnectionFactory
	}

	@Bean
	fun jackson2MessageConverter(): Jackson2JsonMessageConverter = Jackson2JsonMessageConverter()

	@Bean
	fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate? {
		val rabbitTemplate = RabbitTemplate(connectionFactory)
		rabbitTemplate.messageConverter = jackson2MessageConverter()
		return rabbitTemplate
	}
}

fun main(args: Array<String>) {
	runApplication<ApiServiceApplication>(*args)
}
