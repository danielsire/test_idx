package com.idx.dataProducer.service

import com.idx.dataProducer.broker.Broker
import com.idx.dataProducer.component.RandomClientData
import com.idx.dataProducer.dto.ClientDto
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.LinkedHashMap
import kotlin.random.Random

@Service
internal class ClientGeneratorService(
        private val randomClientData: RandomClientData,
        private val broker: Broker
) {

    fun generate(qty:Int):Boolean {

        if (qty >=1) {
            val names = randomClientData.getNames()

            val types = randomClientData.getTypes()

            for (i in 0 until qty) {

                val name = names[Random.nextInt(0, names.size-1)] as LinkedHashMap<String, String>
                val type = types[Random.nextInt(0, types.size-1)] as LinkedHashMap<String, String>
                val surNameExtraChar = RandomStringUtils.randomAlphabetic(6)

                broker.send(
                        ClientDto(
                                identifier = UUID.randomUUID(),
                                firstName = name["firstName"]!!,
                                lastName = "${name["lastName"]} $surNameExtraChar",
                                email = "${name["firstName"]}@${name["lastName"]}${surNameExtraChar}.com".lowercase(),
                                isActive = Random.nextBoolean(),
                                type = type["type"]!!
                        )
                )
            }
            return true
        }
        return false
    }

}